package org.mindia;

import com.google.gson.Gson;
import okhttp3.*;
import org.mindia.exceptions.UnauthorizedException;
import org.mindia.exceptions.UnknownException;
import org.mindia.models.requests.CreateNamedTransformationRequest;
import org.mindia.models.requests.ReadMediaRequest;
import org.mindia.models.requests.ReadNamedTransformationsRequest;
import org.mindia.models.requests.UploadMediaRequest;
import org.mindia.models.results.CreateNamedTransformationResult;
import org.mindia.models.results.ReadMediaResult;
import org.mindia.models.results.ReadNamedTransformationsResult;
import org.mindia.models.results.UploadMediaResult;
import org.mindia.utils.Utils;

import java.io.IOException;

public class Client {
  private final NamedTransformationsHandler namedTransformationsHandler;

  public Client(Config config) {
    namedTransformationsHandler = new NamedTransformationsHandler(config);
  }

  public CreateNamedTransformationResult createNamedTransformation(CreateNamedTransformationRequest request) throws UnauthorizedException, UnknownException {
    return namedTransformationsHandler.createNamedTransformation(request);
  }

  public ReadNamedTransformationsResult readNamedTransformations(ReadNamedTransformationsRequest request) throws UnauthorizedException, UnknownException {
    return namedTransformationsHandler.readNamedTransformations();
  }

  public void deleteAllNamedTransformations() throws UnauthorizedException, UnknownException {
    namedTransformationsHandler.deleteAllNamedTransformations();
  }

/*  public UploadMediaResult uploadMedia(UploadMediaRequest request) throws UnknownException, UnauthorizedException {
    UploadMediaResult result = null;

    MultipartBody.Builder builder = new MultipartBody.Builder();
    builder.setType(MultipartBody.FORM);
    builder
        .setType(MultipartBody.FORM)
        .addFormDataPart("file", request.getFile().getName(), RequestBody.create(MediaType.parse("image/*"),
            request.getFile()));
    RequestBody requestBody = builder.build();

    try {
      Request request2 = new Request.Builder()
          .url(this.getBaseUrl() + "/read" + request.getPath())
          .addHeader("Authorization", "Bearer masterKey")
          .post(requestBody)
          .build();
      Call call = client.newCall(request2);
      Response response = call.execute();
      if (response.code() == 200 && response.body() != null) {
        String respBody = response.body().string();
        result = new Gson().fromJson(respBody, UploadMediaResult.class);
      } else {
        Utils.generalApiThrowException(response);
      }
    } catch(IOException e) {
      throw new UnknownException(e.getMessage(), e.getCause());
    }

    return result;
  }

  public ReadMediaResult readMedia(ReadMediaRequest request) throws UnknownException, UnauthorizedException {
    ReadMediaResult result = null;

    try {
      Request request2 = new Request.Builder()
          .url(this.getBaseUrl() + "/read" + request.getPath())
          .addHeader("Authorization", "Bearer masterKey")
          .build();
      Call call = client.newCall(request2);
      Response response = call.execute();
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
  }*/
}

