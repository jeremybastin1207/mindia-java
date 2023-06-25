package org.mindia.models.requests;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@Builder
public class UploadMediaRequest {
  private String filename;
  private File file;
  private String transformations;
}
