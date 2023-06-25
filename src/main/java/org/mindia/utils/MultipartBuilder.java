package org.mindia.utils;

import lombok.Data;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.util.Base64;

public class MultipartBuilder {
  private static final String RANDOM_BOUNDARY = "randomBoundary-------------------";

  public static MultipartBody build(FileUploadRequest fileUploadRequest) {
    MultipartBody.Builder builder = new MultipartBody.Builder(RANDOM_BOUNDARY).setType(MultipartBody.FORM);
    MediaType mediaType = MediaType.parse("image/*");
    RequestBody body = RequestBody.create(fileUploadRequest.getFile(), mediaType);
    System.out.println(body.contentType());
    builder.addFormDataPart("file", fileUploadRequest.getFilename(), body);
    if (fileUploadRequest.getTransformation() != null) {
      builder.addFormDataPart("transformations", fileUploadRequest.getTransformation());
    }
    return builder.build();
  }
}
