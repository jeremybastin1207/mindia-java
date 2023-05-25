package org.mindia.exceptions;

/** This class wraps MindiaExceptions dealing with Mindia API errors */
public class MindiaApiException extends MindiaException {
  private final APIError error;

  public MindiaApiException(APIError error) {
    super();
    this.error = error;
  }

  public String getMessage() {
    return error.getMessage();
  }

  public String getCode() {
    return error.getCode();
  }

  public String getType() {
    return error.getType();
  }

  public String getLink() {
    return error.getLink();
  }

  @Override
  public String toString() {
    return "Meilisearch ApiException: {" + "Error=" + error + '}';
  }
}
