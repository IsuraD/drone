package io.drone.api.impl;

import io.drone.api.DroneApi;
import io.drone.api.*;
import io.drone.api.dao.DroneDAO;
import io.drone.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;


/**
 * Swagger Drone - OpenAPI 3.0
 *
 * <p>This is a sample definition for loading drones with medicines. 
 *
 */
public class DroneApiServiceImpl implements DroneApi {
    /**
     * Add a new drone
     *
     * Add a new drone
     *
     */
    public Drone addDrone(Drone body) {
        DroneDAO.getInstance().addDrone(body);
        return body;
    }
    
    /**
     * Delate a drone
     *
     * Delate a drone
     *
     */
    public void deleteDrone(String serialNumber) {
        // TODO: Implement...
        
        
    }
    
    /**
     * get available drones for loading
     *
     * Get all available drones for loading
     *
     */
    public List<DroneSearch> getAavailbleDronesToLoad() {
        // TODO: Implement...
        
        return null;
    }
    
    /**
     * get a  drone
     *
     * Get a  drone
     *
     */
    public DroneGet getDrone(String serialNumber) {
        return DroneDAO.getInstance().getDrone(serialNumber);
    }
    
    /**
     * get all drones
     *
     * Get all drones
     *
     */
    public List<DroneGet> getDrones() {
        // TODO: Implement...
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
     *
     * Load medicine to drone
     *
     */
    public LoadDrone loadDrone(LoadDrone body, String serialNumber) {
        return DroneDAO.getInstance().loadDrone(body, serialNumber);
    }
    
    /**
     * Update a new drone
     *
     * Update a  drone
     *
     */
    public Drone updateDrone(Drone body, String serialNumber) {
        // TODO: Implement...
        
        return null;
    }
    
}

