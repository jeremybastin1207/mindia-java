package org.mindia.utils;

import java.io.IOException;
import java.nio.file.Files;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MultipartBuilder {
  private static final String RANDOM_BOUNDARY = "randomBoundary-------------------";

  public static MultipartBody build(FileUploadRequest fileUploadRequest) throws IOException {
    MultipartBody.Builder builder = new MultipartBody.Builder(RANDOM_BOUNDARY).setType(MultipartBody.FORM);
    String mediaType = Files.probeContentType(fileUploadRequest.getFile().toPath());
    if (fileUploadRequest.getTransformation() != null) {
      builder.addFormDataPart("transformations", fileUploadRequest.getTransformation());
    }
    RequestBody body = RequestBody.create(fileUploadRequest.getFile(), MediaType.parse(mediaType));
    builder.addFormDataPart("file", fileUploadRequest.getFilename(), body);
    return builder.build();
  }
}
