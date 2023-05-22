package org.mindia.models.results;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReadNamedTransformationsResult {
  @SerializedName("named_transformations")
  private List<NamedTransformationResult> namedTransformations;

  @Data
  private static class NamedTransformationResult {
    @SerializedName("name")
    private String name;
    @SerializedName("transformations")
    private String transformations;
  }
}
