package io.drone.api.dao;

import io.drone.api.SQLConstants;
import io.drone.model.*;

import javax.ws.rs.BadRequestException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to communicate with the Database layer.
 */
public class DroneDAO {
    private static final DroneDAO droneDAO = new DroneDAO();

    private DroneDAO() {
        // Initialize the initial databases since this is a in-memory database.
        initializeDatabase();
    }

    public static DroneDAO getInstance() {
        return droneDAO;
    }

    /**
     * Add a drone
     *
     * @param body
     */
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

    /**
     * Add a medicine
     *
     * @param medicine
     */
    public void addMedicine(Medicine medicine) {
        try {
            validateInput(medicine);
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


    /**
     * Get a Drone
     *
     * @param serialNumber
     * @return
     */
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

    /**
     * Get a medicine by name
     *
     * @param name
     * @return
     */
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

    /**
     * Load the draone with medicine.
     *
     * @param body
     * @param serialNumber
     * @return
     */
    public LoadDrone loadDrone(LoadDrone body, String serialNumber) {
        //Validate the load.
        validateLoad(body, serialNumber);
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

                PreparedStatement prepStmt2 = connection.prepareStatement(SQLConstants.UPDATE_DRONE_STATE);
                prepStmt2.setString(1, Drone.StateEnum.LOADED.toString());
                prepStmt2.setString(2, serialNumber);
                prepStmt2.execute();
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Error while loading medicine to drone:" + serialNumber + ": " + e);
        }
        return body;
    }

    /**
     * Get the idle drones.
     * @return
     */
    public List<DroneSearch> getAvailableDronesToLoad() {
        try {
            Connection connection = getConnection();
            PreparedStatement prepStmt = connection.prepareStatement(SQLConstants.LOAD_AVAILABLE_DRONES);
            prepStmt.setString(1, Drone.StateEnum.IDLE.toString());
            List<DroneSearch> drones = new ArrayList<>();
            try (ResultSet rs = prepStmt.executeQuery()) {
                while (rs.next()) {
                    DroneSearch droneSearch = new DroneSearch();
                    DroneGet drone = getDrone(rs.getString("SERIAL_NUMBER"));
                    droneSearch.setCurrentLoad(drone.getLoad());
                    droneSearch.setBatteryCapacity(drone.getBatteryCapacity());
                    droneSearch.setState(DroneSearch.StateEnum.IDLE);
                    droneSearch.setSeriealNumber(drone.getSeriealNumber());
                    droneSearch.setWeightLimit(drone.getWeightLimit());
                    droneSearch.setWeightRemaining(drone.getWeightLimit());
                    drones.add(droneSearch);
                }
            }
            return drones;
        } catch (SQLException e) {
            System.out.println("Error while lading the available drones" + e);
        }
        return null;
    }

    private void validateLoad(LoadDrone body, String serialNumber) {
        DroneGet drone = getDrone(serialNumber);
        int maxWeight = drone.getWeightLimit();
        Integer batteryCapacity = drone.getBatteryCapacity();

        int totalWeight = 0;
        for (Medicine medicine : body.getMedicine()) {
            totalWeight = totalWeight + medicine.getWeight();
        }
        if (totalWeight > maxWeight) {
            throw new BadRequestException("Max Allowed weight exceeded.");
        }

        if (batteryCapacity < 25) {
            throw new BadRequestException("Battery capacity is not enough to load the drone.");
        }
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

    private void validateInput(Medicine medicine) {
        String regexName = "^[A-Za-z0-9-_]\\w{0,100}$";
        Pattern pN = Pattern.compile(regexName);
        Matcher mMN = pN.matcher(medicine.getName());
        if (!mMN.matches()) {
            throw new BadRequestException("Invalid medicine name: " + medicine.getName());
        }

        String regexCode = "^[A-Z0-9_]\\w{0,100}$";
        Pattern pC = Pattern.compile(regexCode);
        Matcher mC = pC.matcher(medicine.getCode());
        if (!mC.matches()) {
            throw new BadRequestException("Invalid medicine code: " + medicine.getCode());
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
        DriverManager.registerDriver(new org.h2.Driver());
        return DriverManager.getConnection(jdbcURL);
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

}
