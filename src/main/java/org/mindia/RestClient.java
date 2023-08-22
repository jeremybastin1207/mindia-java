package org.mindia;

import com.google.gson.Gson;
import okhttp3.*;
import org.mindia.utils.FileUploadRequest;
import org.mindia.utils.MultipartBuilder;
import org.mindia.exceptions.APIError;
import org.mindia.exceptions.MindiaApiException;
import org.mindia.exceptions.MindiaException;
import org.mindia.models.requests.CreateNamedTransformationRequest;
import org.mindia.models.requests.ReadMediaRequest;
import org.mindia.models.requests.UploadMediaRequest;
import org.mindia.models.results.CreateNamedTransformationResult;
import org.mindia.models.results.ReadMediaResult;
import org.mindia.models.results.ReadNamedTransformationsResult;
import org.mindia.models.results.UploadMediaResult;

import java.io.IOException;

public class RestClient {
  private static final String UPLOAD_MEDIA_PATH = "/upload";
  private static final String READ_MEDIA_PATH = "/media";
  private static final String NAMED_TRANSFORMATION_PATH = "/named_transformation";

  private final OkHttpClient client;
  private final Config config;

  public RestClient(OkHttpClient client, Config config) {
    this.client = client;
    this.config = config;
  }

  public UploadMediaResult UploadMedia(UploadMediaRequest uploadMediaRequest) throws MindiaException {
    UploadMediaResult result;

    try {
      FileUploadRequest fileUploadRequest = FileUploadRequest.builder()
          .filename(uploadMediaRequest.getFilename())
          .file(uploadMediaRequest.getFile())
          .transformation(uploadMediaRequest.getTransformations())
          .build();

      MultipartBody multipartBody = MultipartBuilder.build(fileUploadRequest);

      Request request = new Request.Builder()
          .url(config.getHost() + UPLOAD_MEDIA_PATH)
          .addHeader("Authorization", this.config.getApiKey())
          .post(multipartBody)
          .build();
      Call call = client.newCall(request);
      Response response = call.execute();

      if (response.code() == 200 && response.body() != null) {
        String respBody = response.body().string();
        result = new Gson().fromJson(respBody, UploadMediaResult.class);
      } else {
        throw new MindiaApiException(new APIError(response.message(), String.valueOf(response.code()), "", ""));
      }
    } catch (IOException e) {
      throw new MindiaException(e.getMessage());
    }
    return result;
  }

  public ReadMediaResult ReadMedia(ReadMediaRequest readMediaRequest) throws MindiaException {
    ReadMediaResult result;

    try {
      Request request = new Request.Builder()
          .url(config.getHost() + READ_MEDIA_PATH + readMediaRequest.getPath())
          .addHeader("Authorization", this.config.getApiKey())
          .get()
          .build();
      Call call = client.newCall(request);
      Response response = call.execute();

      if (response.code() == 200 && response.body() != null) {
        String respBody = response.body().string();
        result = new Gson().fromJson(respBody, ReadMediaResult.class);
      } else {
        throw new MindiaApiException(new APIError(response.message(), String.valueOf(response.code()), "", ""));
      }
    } catch (IOException e) {
      throw new MindiaException(e.getMessage());
    }

    return result;
  }

  public CreateNamedTransformationResult createNamedTransformation(
      CreateNamedTransformationRequest createNamedTransformationRequest) throws MindiaException {
    CreateNamedTransformationResult result;

    try {
      RequestBody body = RequestBody.create(new Gson().toJson(createNamedTransformationRequest).getBytes());

      Request request = new Request.Builder()
          .url(config.getHost() + NAMED_TRANSFORMATION_PATH)
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
    } catch (IOException e) {
      throw new MindiaException(e.getMessage());
    }
    return result;
  }

  public ReadNamedTransformationsResult readNamedTransformations() throws MindiaException {
    ReadNamedTransformationsResult result;

    try {
      Request request = new Request.Builder()
          .url(config.getHost() + NAMED_TRANSFORMATION_PATH)
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
    } catch (IOException e) {
      throw new MindiaException(e.getMessage());
    }
    return result;
  }

  public void deleteAllNamedTransformations() throws MindiaException {
    try {
      Request request = new Request.Builder()
          .url(config.getHost() + NAMED_TRANSFORMATION_PATH)
          .addHeader("Authorization", this.config.getApiKey())
          .delete()
          .build();
      Call call = client.newCall(request);
      Response response = call.execute();

      if (response.code() != 200 || response.body() == null) {
        throw new MindiaApiException(new APIError(response.message(), String.valueOf(response.code()), "", ""));
      }
    } catch (IOException e) {
      throw new MindiaException(e.getMessage());
    }
  }
}
