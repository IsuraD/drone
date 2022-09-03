package io.drone.model;

import javax.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Medicine   {
  
  @Schema(description = "the name of the medicine")
 /**
   * the name of the medicine  
  **/
  private String name = null;
  
  @Schema(example = "10", description = "")
  private Integer weight = null;
  
  @Schema(example = "UUU_10", description = "")
  private String code = null;
  
  @Schema(description = "image of the drone")
 /**
   * image of the drone  
  **/
  private String imageURL = null;
 /**
   * the name of the medicine
   * @return name
  **/
  @JsonProperty("name")
 @Pattern(regexp="^[A-Za-z0-9-_]$")  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Medicine name(String name) {
    this.name = name;
    return this;
  }

 /**
   * Get weight
   * @return weight
  **/
  @JsonProperty("weight")
  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public Medicine weight(Integer weight) {
    this.weight = weight;
    return this;
  }

 /**
   * Get code
   * @return code
  **/
  @JsonProperty("code")
 @Pattern(regexp="^[A-Z0-9-]$")  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Medicine code(String code) {
    this.code = code;
    return this;
  }

 /**
   * image of the drone
   * @return imageURL
  **/
  @JsonProperty("imageURL")
  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  public Medicine imageURL(String imageURL) {
    this.imageURL = imageURL;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Medicine {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    weight: ").append(toIndentedString(weight)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    imageURL: ").append(toIndentedString(imageURL)).append("\n");
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
