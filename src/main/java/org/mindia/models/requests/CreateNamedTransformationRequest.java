package org.mindia.models.requests;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateNamedTransformationRequest {
  @SerializedName("name")
  private String name;
  @SerializedName("transformations")
  private String transformations;
}
