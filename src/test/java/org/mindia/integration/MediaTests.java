package org.mindia.integration;


import com.google.common.io.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mindia.Config;
import org.mindia.MindiaClient;
import org.mindia.models.requests.UploadMediaRequest;
import org.mindia.models.results.UploadMediaResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Tag("integration")
public class MediaTests {
  private MindiaClient mindiaClient;
  private TestUtils testUtils;

  @BeforeEach
  public void setUp() {
    if (mindiaClient == null) {
      mindiaClient = new MindiaClient(new Config("127.0.0.1:3500", "masterKey"));
      testUtils = new TestUtils();
    }
  }

  @Test
  void uploadMediaTest() throws Exception {
    UploadMediaRequest request = UploadMediaRequest.builder()
        .filename("sample1.jpg")
        .file(testUtils.loadFile("samples/sample1.jpg"))
        .transformations("[\"c_scale,w_200,h_200\"]")
        .build();

    UploadMediaResult result = mindiaClient.uploadMedia(request);
    assertEquals("webp", Files.getFileExtension((result.getPath())));
  }
}
