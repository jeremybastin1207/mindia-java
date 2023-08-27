package org.mindia.utils;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@Builder
public class FileUploadRequest {
  private String folder;
  private String filename;
  private String transformation;
  private File file;
}
