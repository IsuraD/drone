package io.drone.api.dao;

import io.drone.api.SQLConstants;
import io.drone.model.Drone;
import io.drone.model.DroneGet;
import io.drone.model.LoadDrone;
import io.drone.model.Medicine;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DroneDAO {
    private static final DroneDAO droneDAO = new DroneDAO();

    private DroneDAO() {
        initializeDatabase();
    }

    public static DroneDAO getInstance() {
        return droneDAO;
    }


    public void addDrone(Drone body) {
        try {
            Connection connection = getConnection();
            PreparedStatement prepStmt = connection.prepareStatement(SQLConstants.ADD_DRONE);
            prepStmt.setString(1, body.getSeriealNumber());
            prepStmt.setString(2, body.getModel());
            prepStmt.setInt(3, body.getWeightLimit());
            prepStmt.setInt(4, body.getBatteryCapacity());
            prepStmt.setString(5, body.getState());
            prepStmt.execute();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Error while adding drone:" + body.getSeriealNumber() + ": " + e);
        }
    }

    public void addMedicine(Medicine medicine) {
        try {
            Connection connection = getConnection();
            PreparedStatement prepStmt = connection.prepareStatement(SQLConstants.ADD_MEDICINE);
            prepStmt.setString(1, medicine.getName());
            prepStmt.setString(3, medicine.getCode());
            prepStmt.setInt(2, medicine.getWeight());
            prepStmt.setString(4, medicine.getImageURL());
            prepStmt.execute();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Error while adding medicine:" + medicine.getName() + ": " + e);
        }
    }

    private void initializeDatabase() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            System.out.println("Connected to H2 in-memory database.");
            statement.execute(SQLConstants.ADD_DRONE_TABLE);
            statement.execute(SQLConstants.ADD_MEDICINE_TABLE);
            statement.execute(SQLConstants.ADD_DRONE_MEDICINE_ASSOC_TABLE);
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Error while creating the database: " + e);
        }
    }

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:h2:mem:test";
        return DriverManager.getConnection(jdbcURL);
    }

    public DroneGet getDrone(String serialNumber) {
        try {
            Connection connection = getConnection();
            PreparedStatement prepStmt = connection.prepareStatement(SQLConstants.LOAD_DRONE);
            prepStmt.setString(1, serialNumber);

            try (ResultSet rs = prepStmt.executeQuery()) {
                if (rs.next()) {
                    DroneGet droneGet = new DroneGet();
                    droneGet.setSeriealNumber(serialNumber);
                    droneGet.setWeightLimit(rs.getInt("WEIGHT_LIMIT"));
                    droneGet.setState(DroneGet.StateEnum.fromValue(rs.getString("STATE")));
                    droneGet.setBatteryCapacity(rs.getInt("BATTERY_CAPACITY"));
                    droneGet.setModel(DroneGet.ModelEnum.fromValue(rs.getString("MODEL")));
                    LoadDrone loadDrone = new LoadDrone();
                    loadDrone.setMedicine(getMedicines(serialNumber));
                    droneGet.setLoad(loadDrone);
                    return droneGet;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while lading the drone:" + serialNumber + ": " + e);
        }
        return null;
    }

    public Medicine getMedicine(String name) {
        try {
            Connection connection = getConnection();
            PreparedStatement prepStmt = connection.prepareStatement(SQLConstants.LOAD_MEDICINE);
            prepStmt.setString(1, name);

            try (ResultSet rs = prepStmt.executeQuery()) {
                if (rs.next()) {
                    Medicine medicine = new Medicine();
                    medicine.setCode(rs.getString("CODE"));
                    medicine.setImageURL(rs.getString("IMAGE_URL"));
                    medicine.setName(name);
                    medicine.setWeight(rs.getInt("WEIGHT"));
                    return medicine;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while lading the medicine:" + name + ": " + e);
        }
        return null;
    }

    private Map<String, Integer> getDroneMedicine(String serialNumber) {
        try {
            Connection connection = getConnection();
            PreparedStatement prepStmt = connection.prepareStatement(SQLConstants.LOAD_DRONE_MEDICINE);
            prepStmt.setString(1, serialNumber);
            Map<String, Integer> medicines = new HashMap<>();
            try (ResultSet rs = prepStmt.executeQuery()) {
                while (rs.next()) {
                    medicines.put(rs.getString("MEDICINE_NAME"), rs.getInt("MEDICINE_COUNT"));
                }
            }

            return medicines;
        } catch (SQLException e) {
            System.out.println("Error while lading the drone medicines:" + serialNumber + ": " + e);
        }
        return null;
    }

    private List<Medicine> getMedicines(String serialNumber) {
        List<Medicine> medicines = new ArrayList<>();
        Map<String, Integer> droneMedicine = getDroneMedicine(serialNumber);
        for (String name : droneMedicine.keySet()) {
            Medicine medicine = getMedicine(name);
            int count = droneMedicine.get(name);
            for (int i = 0; i < count; i++) {
                medicines.add(medicine);
            }
        }
        return medicines;
    }

    public LoadDrone loadDrone(LoadDrone body, String serialNumber) {
        Map<String, Integer> medicines = getMedicineMap(body.getMedicine());
        Map<String, Medicine> nameToMedicineMap = getNameToMedicineMap(body.getMedicine());
        try {
            Connection connection = getConnection();

            for (String medicineName : medicines.keySet()) {
                if (getMedicine(medicineName) == null) {
                    addMedicine(nameToMedicineMap.get(medicineName));
                }
                int count = medicines.get(medicineName);
                PreparedStatement prepStmt = connection.prepareStatement(SQLConstants.ADD_DRONE_MEDICINE);
                prepStmt.setString(1, serialNumber);
                prepStmt.setString(2, medicineName);
                prepStmt.setInt(3, count);
                prepStmt.execute();
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Error while loading medicine to drone:" + serialNumber + ": " + e);
        }
        return body;
    }

    private Map<String, Integer> getMedicineMap(List<Medicine> medicines) {
        Map<String, Integer> map = new HashMap<>();
        for (Medicine medicine : medicines) {
            if (map.get(medicine.getName()) == null) {
                map.put(medicine.getName(), 1);
            } else {
                map.put(medicine.getName(), map.get(medicine.getName() + 1));
            }
        }
        return map;
    }

    private Map<String, Medicine> getNameToMedicineMap(List<Medicine> medicines) {
        Map<String, Medicine> map = new HashMap<>();
        for (Medicine medicine : medicines) {
            map.putIfAbsent(medicine.getName(), medicine);
        }
        return map;
    }
}
