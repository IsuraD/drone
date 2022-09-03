package io.drone.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoadDrone   {
  
  @Schema(description = "")
  private List<Medicine> medicine = null;
 /**
   * Get medicine
   * @return medicine
  **/
  @JsonProperty("medicine")
  public List<Medicine> getMedicine() {
    return medicine;
  }

  public void setMedicine(List<Medicine> medicine) {
    this.medicine = medicine;
  }

  public LoadDrone medicine(List<Medicine> medicine) {
    this.medicine = medicine;
    return this;
  }

  public LoadDrone addMedicineItem(Medicine medicineItem) {
    this.medicine.add(medicineItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoadDrone {\n");
    
    sb.append("    medicine: ").append(toIndentedString(medicine)).append("\n");
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
