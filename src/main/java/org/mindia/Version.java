package org.mindia;

public class Version {
  static final String VERSION = "0";

  public static String getQualifiedVersion() {
    return "Mindia Java (v" + VERSION + ")";
  }

  public static String getVersion() {
    return "v" + VERSION;
  }
}
