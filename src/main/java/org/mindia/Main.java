package org.mindia;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello world!");

    MediaClient mediaClient = new MediaClient();

    ReadMediaRequest request = new ReadMediaRequest("/houses/02844705-67d1-4cf0-80f0-865430fe3ee8.webp");
    ReadMediaResponse response = mediaClient.readMedia(request);
    System.out.println(response.toString());
  }
}