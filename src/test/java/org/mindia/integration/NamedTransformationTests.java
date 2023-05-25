package org.mindia.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mindia.Client;
import org.mindia.Config;
import org.mindia.models.requests.CreateNamedTransformationRequest;
import org.mindia.models.results.CreateNamedTransformationResult;

import static org.junit.jupiter.api.Assertions.*;

@Tag("integration")
public class NamedTransformationTests {
  private Client client;

  @BeforeEach
  public void setUp() {
    if (client == null) {
      client = new Client(new Config("127.0.0.1:3500", "masterKey"));
    }
  }

  @AfterAll
  static void clean() {
    try {
      Client client = new Client(new Config("127.0.0.1:3500", "masterKey"));
      client.deleteAllNamedTransformations();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void createNamedTransformationTest() throws Exception {
    CreateNamedTransformationRequest request = CreateNamedTransformationRequest.builder()
        .name("thumbnail")
        .transformations("c_scale,h_450,w_450")
        .build();

    CreateNamedTransformationResult result = client.createNamedTransformation(request);
    assertEquals("thumbnail", result.getName());
    assertEquals("c_scale,h_450,w_450", result.getTransformations());
  }
}
