package org.mindia.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mindia.MindiaClient;
import org.mindia.Config;
import org.mindia.models.requests.CreateNamedTransformationRequest;
import org.mindia.models.results.CreateNamedTransformationResult;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

@Tag("integration")
public class NamedTransformationTests {
  private static MindiaClient mindiaClient;
  private static MockWebServer mockBackEnd;

  @BeforeAll
  static void setUp() throws IOException {
    mockBackEnd = new MockWebServer();
    mockBackEnd.start();
  }

  @AfterAll
  static void tearDown() throws IOException {
    mockBackEnd.shutdown();
  }

  @BeforeEach
  void initialize() {
    String baseUrl = String.format("%s:%s", mockBackEnd.getHostName(), mockBackEnd.getPort());
    mindiaClient = new MindiaClient(new Config(baseUrl, "masterKey"));
  }

  @Test
  void createNamedTransformationTest() throws Exception {
    mockBackEnd.enqueue(new MockResponse()
        .setBody("{ \"name\": \"thumbnail\", \"transformations\": \"c_scale,h_450,w_450\" }")
        .addHeader("Content-Type", "application/json"));

    CreateNamedTransformationRequest request = CreateNamedTransformationRequest.builder()
        .name("thumbnail")
        .transformations("c_scale,h_450,w_450")
        .build();

    CreateNamedTransformationResult result = mindiaClient.createNamedTransformation(request);
    assertEquals("thumbnail", result.getName());
    assertEquals("c_scale,h_450,w_450", result.getTransformations());
  }
}
