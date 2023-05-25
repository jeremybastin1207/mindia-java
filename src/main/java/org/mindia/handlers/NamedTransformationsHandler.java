package org.mindia.handlers;

import com.google.gson.Gson;
import okhttp3.*;
import org.mindia.Config;
import org.mindia.exceptions.APIError;
import org.mindia.exceptions.MindiaApiException;
import org.mindia.exceptions.MindiaException;
import org.mindia.models.requests.CreateNamedTransformationRequest;
import org.mindia.models.results.CreateNamedTransformationResult;
import org.mindia.models.results.ReadNamedTransformationsResult;

import java.io.IOException;

public class NamedTransformationsHandler {
  private final OkHttpClient client;
  private final Config config;

  public NamedTransformationsHandler(OkHttpClient client, Config config) {
    this.client = client;
    this.config = config;
  }

  public String getNamedTransformationPath() {
    return this.config.getHost() + "/named_transformation";
  }

  public CreateNamedTransformationResult createNamedTransformation(CreateNamedTransformationRequest createNamedTransformationRequest) throws MindiaException {
    CreateNamedTransformationResult result;

    try {
      RequestBody body = RequestBody.create(new Gson().toJson(createNamedTransformationRequest).getBytes());

      Request request = new Request.Builder()
          .url(this.getNamedTransformationPath())
          .addHeader("Authorization", this.config.getApiKey())
          .post(body)
          .build();
      Call call = client.newCall(request);
      Response response = call.execute();

      if (response.code() == 200 && response.body() != null) {
        String respBody = response.body().string();
        result = new Gson().fromJson(respBody, CreateNamedTransformationResult.class);
      } else {
        throw new MindiaApiException(new APIError(response.message(), String.valueOf(response.code()), "", ""));
      }
    } catch(IOException e) {
      throw new MindiaException(e.getMessage());
    }
    return result;
  }

  public ReadNamedTransformationsResult readNamedTransformations() throws MindiaException {
    ReadNamedTransformationsResult result;

    try {
      Request request = new Request.Builder()
          .url(this.getNamedTransformationPath())
          .addHeader("Authorization", this.config.getApiKey())
          .build();
      Call call = client.newCall(request);
      Response response = call.execute();

      if (response.code() == 200 && response.body() != null) {
        String respBody = response.body().string();
        result = new Gson().fromJson(respBody, ReadNamedTransformationsResult.class);
      } else {
        throw new MindiaApiException(new APIError(response.message(), String.valueOf(response.code()), "", ""));
      }
    } catch(IOException e) {
      throw new MindiaException(e.getMessage());
    }
    return result;
  }

  public void deleteAllNamedTransformations() throws MindiaException {
    try {
      Request request = new Request.Builder()
          .url(this.getNamedTransformationPath())
          .addHeader("Authorization", this.config.getApiKey())
          .delete()
          .build();
      Call call = client.newCall(request);
      Response response = call.execute();

      if (response.code() != 200 || response.body() == null) {
        throw new MindiaApiException(new APIError(response.message(), String.valueOf(response.code()), "", ""));
      }
    } catch(IOException e) {
      throw new MindiaException(e.getMessage());
    }
  }
}
