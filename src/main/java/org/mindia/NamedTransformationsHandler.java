package org.mindia;

import com.google.gson.Gson;
import okhttp3.*;
import org.mindia.exceptions.UnauthorizedException;
import org.mindia.exceptions.UnknownException;
import org.mindia.models.requests.CreateNamedTransformationRequest;
import org.mindia.models.requests.ReadNamedTransformationsRequest;
import org.mindia.models.results.CreateNamedTransformationResult;
import org.mindia.models.results.ReadNamedTransformationsResult;
import org.mindia.utils.Utils;

import java.io.IOException;

public class NamedTransformationsHandler {
  private final Config config;
  private final OkHttpClient client;

  public NamedTransformationsHandler(Config config) {
    this.config = config;
    this.client = new OkHttpClient();
  }

  public String getNamedTransformationPath() {
    return this.config.getHost() + "/" + Version.getVersion() + "/named_transformation";
  }

  public CreateNamedTransformationResult createNamedTransformation(CreateNamedTransformationRequest createNamedTransformationRequest) throws UnauthorizedException, UnknownException {
    CreateNamedTransformationResult result = null;

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
        Utils.generalApiThrowException(response);
      }
    } catch(IOException e) {
      throw new UnknownException(e.getMessage(), e.getCause());
    }
    return result;
  }

  public ReadNamedTransformationsResult readNamedTransformations() throws UnauthorizedException, UnknownException {
    ReadNamedTransformationsResult result = null;

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
        Utils.generalApiThrowException(response);
      }
    } catch(IOException e) {
      throw new UnknownException(e.getMessage(), e.getCause());
    }
    return result;
  }

  public void deleteAllNamedTransformations() throws UnauthorizedException, UnknownException {
    try {
      Request request = new Request.Builder()
          .url(this.getNamedTransformationPath())
          .addHeader("Authorization", this.config.getApiKey())
          .delete()
          .build();
      Call call = client.newCall(request);
      Response response = call.execute();

      if (response.code() != 200 || response.body() == null) {
        Utils.generalApiThrowException(response);
      }
    } catch(IOException e) {
      throw new UnknownException(e.getMessage(), e.getCause());
    }
  }
}
