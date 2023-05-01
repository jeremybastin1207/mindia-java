package org.mindia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
public class ReadMediaResponse {
  @JsonProperty("content_type")
  private String contentType;

  @JsonProperty("content_length")
  private String contentLength;
}
