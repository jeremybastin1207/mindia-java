package org.mindia.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mindia.MindiaClient;
import org.mindia.Config;
import org.mindia.models.requests.CreateNamedTransformationRequest;
import org.mindia.models.results.CreateNamedTransformationResult;

import static org.junit.jupiter.api.Assertions.*;

@Tag("integration")
public class NamedTransformationTests {
  private MindiaClient mindiaClient;

  @BeforeEach
  public void setUp() {
    if (mindiaClient == null) {
      mindiaClient = new MindiaClient(new Config("127.0.0.1:3500", "masterKey"));
    }
  }

  @AfterAll
  static void clean() {
    try {
      MindiaClient mindiaClient = new MindiaClient(new Config("127.0.0.1:3500", "masterKey"));
      mindiaClient.deleteAllNamedTransformations();
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

    CreateNamedTransformationResult result = mindiaClient.createNamedTransformation(request);
    assertEquals("thumbnail", result.getName());
    assertEquals("c_scale,h_450,w_450", result.getTransformations());
  }
}
