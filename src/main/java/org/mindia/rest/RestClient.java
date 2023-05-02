package org.mindia.rest;

import com.google.gson.Gson;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.mindia.exceptions.UnauthorizedException;
import org.mindia.exceptions.UnknownException;
import org.mindia.models.requests.ReadMediaRequest;
import org.mindia.models.results.ReadMediaResult;
import org.mindia.utils.Utils;

import java.io.IOException;

public class RestClient {
  private final static String BASE_URL = "http://localhost:3500/v0/read";
  private final OkHttpClient client;

  public RestClient() {
    this.client = new OkHttpClient();
  }

  public ReadMediaResult readMedia(ReadMediaRequest params) throws UnknownException, UnauthorizedException {
    ReadMediaResult result = null;
    try {
      Request request = new Request.Builder()
          .url(BASE_URL + params.getPath())
          .addHeader("Authorization", "Bearer masterKey")
          .build();
      Call call = client.newCall(request);
      Response response = call.execute();
      System.out.println(response.code());
      if (response.code() == 200 && response.body() != null) {
        String respBody = response.body().string();
        result = new Gson().fromJson(respBody, ReadMediaResult.class);
      } else {
        Utils.generalApiThrowException(response);
      }
    } catch(IOException e) {
      throw new UnknownException(e.getMessage(), e.getCause());
    }
    return result;
  }
}

