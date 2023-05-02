package org.mindia.utils;

import okhttp3.Response;
import org.mindia.exceptions.UnauthorizedException;
import org.mindia.exceptions.UnknownException;

import java.io.IOException;

public class Utils {

  public static void generalApiThrowException(Response response)
      throws IOException, UnauthorizedException, UnknownException {
    if (response.code() == 401) {
      throw new UnauthorizedException(response.message(), null, false, false, response.message(), null, null);
    } else {
      throw new UnknownException(response.message(), null, false, false, response.message(), null, null);
    }
  }
}
