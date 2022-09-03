package io.drone.model;

import javax.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Drone   {
  
  @Schema(example = "101313-ad2233s-adadsf3s-adafdfasdfda", required = true, description = "")
  private String seriealNumber = null;
  public enum ModelEnum {
    LIGHTWEIGHT("Lightweight"),
    MIDDLEWEIGHT("Middleweight"),
    CRUISERWEIGHT("Cruiserweight"),
    HEAVYWEIGHT("Heavyweight");

    private String value;

    ModelEnum(String value) {
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
    public static ModelEnum fromValue(String text) {
      for (ModelEnum b : ModelEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }  
  @Schema(example = "Lightweight", required = true, description = "the model of the drone")
 /**
   * the model of the drone  
  **/
  private ModelEnum model = null;
  
  @Schema(example = "10", required = true, description = "")
  private Integer weightLimit = null;
  
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
 @Size(max=100)  public String getSeriealNumber() {
    return seriealNumber;
  }

  public void setSeriealNumber(String seriealNumber) {
    this.seriealNumber = seriealNumber;
  }

  public Drone seriealNumber(String seriealNumber) {
    this.seriealNumber = seriealNumber;
    return this;
  }

 /**
   * the model of the drone
   * @return model
  **/
  @JsonProperty("model")
  @NotNull
  public String getModel() {
    if (model == null) {
      return null;
    }
    return model.getValue();
  }

  public void setModel(ModelEnum model) {
    this.model = model;
  }

  public Drone model(ModelEnum model) {
    this.model = model;
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

  public Drone weightLimit(Integer weightLimit) {
    this.weightLimit = weightLimit;
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

  public Drone batteryCapacity(Integer batteryCapacity) {
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

  public Drone state(StateEnum state) {
    this.state = state;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Drone {\n");
    
    sb.append("    seriealNumber: ").append(toIndentedString(seriealNumber)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    weightLimit: ").append(toIndentedString(weightLimit)).append("\n");
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
