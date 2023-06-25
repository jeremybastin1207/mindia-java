package org.mindia.integration;

import java.io.File;
import java.net.URL;

public class TestUtils {
  public File loadFile(String resourcePath) {
    URL sampleURL = getClass().getClassLoader().getResource(resourcePath);
    assert sampleURL != null;
    return new File(sampleURL.getPath());
  }
}
