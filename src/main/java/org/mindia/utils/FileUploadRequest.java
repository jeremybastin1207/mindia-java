package org.mindia.utils;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@Builder
public class FileUploadRequest {
  private String filename;
  private File file;
  private String transformation;
}
