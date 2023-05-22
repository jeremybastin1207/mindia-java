package org.mindia.utils;

import okhttp3.Response;
import org.mindia.exceptions.UnauthorizedException;
import org.mindia.exceptions.UnknownException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class Utils {
  public static byte[] fileToBytes(File file) {
    byte[] bytes = null;
    try (FileInputStream imageInFile = new FileInputStream(file)) {
      // Reading a file from file system
      bytes = new byte[(int) file.length()];
      imageInFile.read(bytes);
    } catch (FileNotFoundException e) {
      System.out.println("File not found" + e);
    } catch (IOException ioe) {
      System.out.println("Exception while reading the file " + ioe);
    }
    return bytes;
  }

  public static String bytesToBase64(byte[] fileData) {
    return Base64.getEncoder().encodeToString(fileData);
  }


  public static void generalApiThrowException(Response response)
      throws IOException, UnauthorizedException, UnknownException {
    if (response.code() == 401) {
      throw new UnauthorizedException(response.message(), null, false, false, response.message(), null, null);
    } else {
      throw new UnknownException(response.message(), null, false, false, response.message(), null, null);
    }
  }
}
