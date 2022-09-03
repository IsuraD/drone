package io.drone.api.impl;

import io.drone.api.DroneApi;
import io.drone.api.*;
import io.drone.model.Drone;
import io.drone.model.DroneGet;
import io.drone.model.DroneSearch;
import io.drone.model.LoadDrone;

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
        // TODO: Implement...
        
        return null;
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
        // TODO: Implement...
        
        return null;
    }
    
    /**
     * get all drones
     *
     * Get all drones
     *
     */
    public List<DroneGet> getDrones() {
        // TODO: Implement...
        
        return null;
    }
    
    /**
     * Load medicine to drone
     *
     * Load medicine to drone
     *
     */
    public LoadDrone loadDrone(LoadDrone body, String serialNumber) {
        // TODO: Implement...
        
        return null;
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

