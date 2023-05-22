package org.mindia.models.requests;

import okhttp3.MultipartBody;
import org.mindia.utils.Utils;

public class MultipartBuilder {
  private static final String RANDOM_BOUNDARY = "randomBoundary-------------------";

  public MultipartBody build(FileRequest fileRequest) {
    MultipartBody.Builder builder = new MultipartBody.Builder(RANDOM_BOUNDARY).setType(MultipartBody.FORM);

    if (null != fileRequest.url) {
      builder.addFormDataPart("file", fileRequest.url.toString());
    } else if (null != fileRequest.base64) {
      builder.addFormDataPart("file", fileRequest.base64);
    } else if (null != fileRequest.bytes) {
      builder.addFormDataPart("file", Utils.bytesToBase64(fileRequest.bytes));
    } else {
      throw new RuntimeException("Error: File not provided.");
    }
    if (null != fileRequest.fileName) {
      builder.addFormDataPart("filename", fileRequest.fileName);
    } else {
      throw new RuntimeException("Error: Filename not provided");
    }

    return builder.build();
  }
}