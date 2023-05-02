package org.mindia.models.results;

import lombok.Data;

import java.util.Map;

@Data
public class MetadataResponse {
  private String raw;
  private int httpStatusCode;
  private Map<String, String> headers;

  @Override
  public String toString() {
    return "ResponseMetaData{" + "raw='" + raw + '\'' + ", httpStatusCode=" + httpStatusCode + ", headers="
        + headers + '}';
  }
}
