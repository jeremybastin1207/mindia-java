package org.mindia.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadMediaRequest {
  private String path;
}
