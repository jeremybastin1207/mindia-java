package org.mindia.exceptions;

import lombok.Getter;
import lombok.Setter;

/** Generic class for Meilisearch Exception handling */
@Getter
@Setter
public class MindiaException extends Exception {
  private String error;
  private String name;

  public MindiaException() {}

  public MindiaException(String error) {
    super(error);
    this.setError(error);
    this.name = this.getClass().getName();
  }

  public MindiaException(Exception e) {
    super(e);
    this.error = e.toString();
    this.name = e.getClass().getName();
  }

  public MindiaException(Throwable cause) {
    super(cause);
    this.error = cause.toString();
    this.name = cause.getClass().getName();
  }

  @Override
  public String toString() {
    return "Mindia Exception: {" + this.name + ". Error=" + this.error + '}';
  }
}
