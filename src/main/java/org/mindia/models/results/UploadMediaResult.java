package org.mindia.models.results;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class UploadMediaResult {
  @SerializedName("path")
  private String path;
}
