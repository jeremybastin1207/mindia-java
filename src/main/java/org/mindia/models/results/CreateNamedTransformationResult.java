package org.mindia.models.results;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CreateNamedTransformationResult {
  @SerializedName("name")
  private String name;
  @SerializedName("transformations")
  private String transformations;
}
