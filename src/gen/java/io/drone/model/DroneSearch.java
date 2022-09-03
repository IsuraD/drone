package io.drone.model;

import javax.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

public class DroneSearch   {
  
  @Schema(example = "101313-ad2233s-adadsf3s-adafdfasdfda", required = true, description = "")
  private String seriealNumber = null;
  
  @Schema(example = "100", required = true, description = "")
  private Integer weightLimit = null;
  
  @Schema(example = "10", description = "")
  private Integer weightRemaining = null;
  
  @Schema(description = "")
  private LoadDrone currentLoad = null;
  
  @Schema(example = "10", description = "")
  private Integer batteryCapacity = null;
  public enum StateEnum {
    IDLE("IDLE"),
    LOADING("LOADING"),
    LOADED("LOADED"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"),
    RETURNING("RETURNING");

    private String value;

    StateEnum(String value) {
      this.value = value;
    }
    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    @JsonCreator
    public static StateEnum fromValue(String text) {
      for (StateEnum b : StateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }  
  @Schema(required = true, description = "pet state of the drone")
 /**
   * pet state of the drone  
  **/
  private StateEnum state = null;
 /**
   * Get seriealNumber
   * @return seriealNumber
  **/
  @JsonProperty("serieal-number")
  @NotNull
  public String getSeriealNumber() {
    return seriealNumber;
  }

  public void setSeriealNumber(String seriealNumber) {
    this.seriealNumber = seriealNumber;
  }

  public DroneSearch seriealNumber(String seriealNumber) {
    this.seriealNumber = seriealNumber;
    return this;
  }

 /**
   * Get weightLimit
   * @return weightLimit
  **/
  @JsonProperty("weight-limit")
  @NotNull
  public Integer getWeightLimit() {
    return weightLimit;
  }

  public void setWeightLimit(Integer weightLimit) {
    this.weightLimit = weightLimit;
  }

  public DroneSearch weightLimit(Integer weightLimit) {
    this.weightLimit = weightLimit;
    return this;
  }

 /**
   * Get weightRemaining
   * @return weightRemaining
  **/
  @JsonProperty("weight-remaining")
  public Integer getWeightRemaining() {
    return weightRemaining;
  }

  public void setWeightRemaining(Integer weightRemaining) {
    this.weightRemaining = weightRemaining;
  }

  public DroneSearch weightRemaining(Integer weightRemaining) {
    this.weightRemaining = weightRemaining;
    return this;
  }

 /**
   * Get currentLoad
   * @return currentLoad
  **/
  @JsonProperty("current-load")
  public LoadDrone getCurrentLoad() {
    return currentLoad;
  }

  public void setCurrentLoad(LoadDrone currentLoad) {
    this.currentLoad = currentLoad;
  }

  public DroneSearch currentLoad(LoadDrone currentLoad) {
    this.currentLoad = currentLoad;
    return this;
  }

 /**
   * Get batteryCapacity
   * @return batteryCapacity
  **/
  @JsonProperty("battery-capacity")
  public Integer getBatteryCapacity() {
    return batteryCapacity;
  }

  public void setBatteryCapacity(Integer batteryCapacity) {
    this.batteryCapacity = batteryCapacity;
  }

  public DroneSearch batteryCapacity(Integer batteryCapacity) {
    this.batteryCapacity = batteryCapacity;
    return this;
  }

 /**
   * pet state of the drone
   * @return state
  **/
  @JsonProperty("state")
  @NotNull
  public String getState() {
    if (state == null) {
      return null;
    }
    return state.getValue();
  }

  public void setState(StateEnum state) {
    this.state = state;
  }

  public DroneSearch state(StateEnum state) {
    this.state = state;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DroneSearch {\n");
    
    sb.append("    seriealNumber: ").append(toIndentedString(seriealNumber)).append("\n");
    sb.append("    weightLimit: ").append(toIndentedString(weightLimit)).append("\n");
    sb.append("    weightRemaining: ").append(toIndentedString(weightRemaining)).append("\n");
    sb.append("    currentLoad: ").append(toIndentedString(currentLoad)).append("\n");
    sb.append("    batteryCapacity: ").append(toIndentedString(batteryCapacity)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
