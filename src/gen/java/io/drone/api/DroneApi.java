package io.drone.api;

import io.drone.model.ErrorResponse;
import io.drone.model.Drone;
import io.drone.model.DroneGet;
import io.drone.model.DroneSearch;
import io.drone.model.LoadDrone;

import java.util.List;
import javax.ws.rs.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;

/**
 * Swagger Drone - OpenAPI 3.0
 *
 * <p>This is a sample definition for loading drones with medicines. 
 *
 */
@Path("/")
public interface DroneApi  {

    /**
     * Add a new drone
     *
     * Add a new drone
     *
     */
    @POST
    @Path("/drones")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(summary = "Add a new drone", tags={ "drone" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Successful Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Drone.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
    public Drone addDrone(@Valid Drone body);

    /**
     * Delate a drone
     *
     * Delate a drone
     *
     */
    @DELETE
    @Path("/drones/{serial-number}")
    @Produces({ "application/json" })
    @Operation(summary = "Delate a drone", tags={ "drone" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "204", description = "No Content"),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
    public void deleteDrone(@PathParam("serial-number") String serialNumber);

    /**
     * get available drones for loading
     *
     * Get all available drones for loading
     *
     */
    @GET
    @Path("/drones/search")
    @Produces({ "application/json" })
    @Operation(summary = "get available drones for loading", tags={ "drone" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "All available drones for loading", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DroneSearch.class)))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
    public List<DroneSearch> getAavailbleDronesToLoad();

    /**
     * get a  drone
     *
     * Get a  drone
     *
     */
    @GET
    @Path("/drones/{serial-number}")
    @Produces({ "application/json" })
    @Operation(summary = "get a  drone", tags={ "drone" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DroneGet.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
    public DroneGet getDrone(@PathParam("serial-number") String serialNumber);

    /**
     * get all drones
     *
     * Get all drones
     *
     */
    @GET
    @Path("/drones")
    @Produces({ "application/json" })
    @Operation(summary = "get all drones", tags={ "drone" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "All Drones", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DroneGet.class)))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
    public List<DroneGet> getDrones();

    /**
     * Load medicine to drone
     *
     * Load medicine to drone
     *
     */
    @PUT
    @Path("/drones/{serial-number}/load")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(summary = "Load medicine to drone", tags={ "drone" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoadDrone.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
    public LoadDrone loadDrone(@Valid LoadDrone body, @PathParam("serial-number") String serialNumber);

    /**
     * Update a new drone
     *
     * Update a  drone
     *
     */
    @PUT
    @Path("/drones/{serial-number}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(summary = "Update a new drone", tags={ "drone" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Drone.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
    public Drone updateDrone(@Valid Drone body, @PathParam("serial-number") String serialNumber);
}
