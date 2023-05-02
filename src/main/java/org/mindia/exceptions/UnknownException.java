package org.mindia.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mindia.models.results.MetadataResponse;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnknownException extends Exception {
  private String message;
  private String help;
  private MetadataResponse metadataResponse;

  public UnknownException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnknownException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                          String message1, String help, MetadataResponse metadataResponse) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.message = message1;
    this.help = help;
    this.metadataResponse = metadataResponse;
  }

  @Override
  public String toString() {
    return "UnknownException{" + "message='" + message + '\'' + ", help='" + help + '\'' + ", responseMetaData="
        + metadataResponse + '}';
  }
}
