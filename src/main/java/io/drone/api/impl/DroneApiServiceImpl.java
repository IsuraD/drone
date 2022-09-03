package io.drone.api.impl;

import io.drone.api.DroneApi;
import io.drone.api.dao.DroneDAO;
import io.drone.model.Drone;
import io.drone.model.DroneGet;
import io.drone.model.DroneSearch;
import io.drone.model.LoadDrone;

import javax.ws.rs.BadRequestException;
import java.util.ArrayList;
import java.util.List;


/**
 * Swagger Drone - OpenAPI 3.0
 *
 * <p>This is a sample definition for loading drones with medicines.
 */
public class DroneApiServiceImpl implements DroneApi {
    /**
     * Add a new drone
     * <p>
     *
     */
    public Drone addDrone(Drone body) {
        validateInputs(body);
        DroneDAO.getInstance().addDrone(body);
        return body;
    }

    private void validateInputs(Drone body) {
        if (body.getSeriealNumber().length() > 100) {
            throw new BadRequestException("Invalid Serial number. Maximum Length should be 100");
        }

        if (body.getBatteryCapacity() > 100) {
            throw new BadRequestException("Invalid Battery capacity %. Should be less than 100");
        }

        if (body.getWeightLimit() > 500) {
            throw new BadRequestException("Invalid Weight Limit. Should be less than 500gr");
        }

        if (body.getModel() == null) {
            throw new BadRequestException("Invalid Model");
        }

        if (body.getState() == null) {
            throw new BadRequestException("Invalid State");
        }
    }

    /**
     * Delate a drone
     * <p>
     *
     */
    public void deleteDrone(String serialNumber) {
        // TODO: Implement...
    }

    /**
     * get available drones for loading
     * <p>
     * Get all available drones for loading
     */
    public List<DroneSearch> getAavailbleDronesToLoad() {
        return DroneDAO.getInstance().getAvailableDronesToLoad();
    }

    /**
     * get a  drone
     * <p>
     *
     */
    public DroneGet getDrone(String serialNumber) {
        return DroneDAO.getInstance().getDrone(serialNumber);
    }

    /**
     * get all drones
     * <p>
     *
     */
    public List<DroneGet> getDrones() {
        DroneGet droneGet = new DroneGet();
        droneGet.setBatteryCapacity(50);
        droneGet.setModel(DroneGet.ModelEnum.HEAVYWEIGHT);
        droneGet.setState(DroneGet.StateEnum.DELIVERING);
        droneGet.setSeriealNumber("asdfadfadsfads");
        droneGet.setWeightLimit(50);
        List<DroneGet> list = new ArrayList<>();
        list.add(droneGet);

        return list;
    }

    /**
     * Load medicine to drone
     * <p>
     *
     */
    public LoadDrone loadDrone(LoadDrone body, String serialNumber) {
        return DroneDAO.getInstance().loadDrone(body, serialNumber);
    }

    /**
     * Update a new drone
     * <p>
     *
     */
    public Drone updateDrone(Drone body, String serialNumber) {
        //TODO
        return null;
    }

}

