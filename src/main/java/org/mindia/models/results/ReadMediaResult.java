package org.mindia.models.results;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ReadMediaResult {
  private String path;

  @SerializedName("content_type")
  private String contentType;

  @SerializedName("content_length")
  private String contentLength;
}
