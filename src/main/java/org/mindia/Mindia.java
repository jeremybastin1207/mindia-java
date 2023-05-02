package org.mindia;

import org.mindia.exceptions.UnauthorizedException;
import org.mindia.exceptions.UnknownException;
import org.mindia.models.requests.ReadMediaRequest;
import org.mindia.models.results.ReadMediaResult;
import org.mindia.rest.RestClient;

public final class Mindia {
  private static Mindia mindia;
  private RestClient restClient;

  public static synchronized Mindia getInstance() {
    if (mindia == null) {
      mindia = new Mindia();
      mindia.restClient = new RestClient();
    }
    return mindia;
  }

  public ReadMediaResult readMedia(ReadMediaRequest request) throws UnknownException, UnauthorizedException {
    return this.restClient.readMedia(request);
  }
}
