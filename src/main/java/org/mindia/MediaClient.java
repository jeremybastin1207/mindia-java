package org.mindia;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MediaClient {
  private final static String BASE_URL = "http://localhost:3500/v0/read";
  private final Client client;

  public MediaClient() {
    client = ClientBuilder.newClient();
  }

  public ReadMediaResponse readMedia(ReadMediaRequest request) {
    Response response = client
        .target(BASE_URL)
        .path(request.getPath())
        .request(MediaType.APPLICATION_JSON)
        .header("Authorization", "Bearer: masterKey")
        .accept(MediaType.APPLICATION_JSON)
        .get();
    System.out.println(response.toString());
    return response.readEntity(ReadMediaResponse.class);
  }
}

