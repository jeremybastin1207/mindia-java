package org.mindia.models.requests;

import lombok.Data;

import java.io.File;

@Data
public class UploadMediaRequest {
  private String path;
  private File file;
}
