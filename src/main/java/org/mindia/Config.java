package org.mindia;

public class Config {
  private final String host;
  private final String apikey;

  public Config(String host, String apikey) {
    this.host = host;
    this.apikey = apikey;
  }

  public String getHost() {
    return "http://" + host + "/" + Version.getVersion();
  }

  /**
   * Method for returning the concatenated Bearer header with apiKey
   *
   * @return Bearer API key String
   */
  public String getApiKey() {
    return "Bearer " + apikey;
  }
}
