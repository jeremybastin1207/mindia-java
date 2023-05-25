package org.mindia.integration;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mindia.exceptions.APIError;
import org.mindia.exceptions.MindiaApiException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("integration")
public class ExceptionsTests {
  /** Test MindiaApiException serialization and getters */
  @Test
  public void testErrorSerializeAndGetters() {
    String message = "You must have an authorization token";
    String code = "missing_authorization_header";
    String type = "authentication_error";
    String link = "https://docs.mindia.com/errors#missing_authorization_header";
    try {
      throw new MindiaApiException(new APIError(message, code, type, link));
    } catch (MindiaApiException e) {
      assertEquals(message, e.getMessage());
      assertEquals(code, e.getCode());
      assertEquals(type, e.getType());
      assertEquals(link, e.getLink());
    }
  }
}
