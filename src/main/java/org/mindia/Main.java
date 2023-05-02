package org.mindia;

import org.mindia.models.requests.ReadMediaRequest;
import org.mindia.models.results.ReadMediaResult;
import org.mindia.rest.RestClient;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello world!");

    RestClient restClient = new RestClient();

    Mindia mindia = Mindia.getInstance();

    try {
      ReadMediaRequest request = new ReadMediaRequest("/houses/02844705-67d1-4cf0-80f0-865430fe3ee8.webp");
      ReadMediaResult response = mindia.readMedia(request);
      System.out.println(response.toString());
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}