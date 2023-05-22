package org.mindia.models.requests;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteNamedTransformationRequest {
  @SerializedName("name")
  private String name;
}
