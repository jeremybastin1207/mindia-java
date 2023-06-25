package org.mindia;

import okhttp3.OkHttpClient;
import org.mindia.exceptions.MindiaException;
import org.mindia.models.requests.CreateNamedTransformationRequest;
import org.mindia.models.requests.ReadNamedTransformationsRequest;
import org.mindia.models.requests.UploadMediaRequest;
import org.mindia.models.results.CreateNamedTransformationResult;
import org.mindia.models.results.ReadNamedTransformationsResult;
import org.mindia.models.results.UploadMediaResult;

public class MindiaClient {
  private final RestClient restClient;

  public MindiaClient(Config config) {
    restClient = new RestClient(new OkHttpClient(), config);
  }

  public UploadMediaResult uploadMedia(UploadMediaRequest uploadMediaRequest) throws MindiaException {
    return restClient.UploadMedia(uploadMediaRequest);
  }

  public CreateNamedTransformationResult createNamedTransformation(CreateNamedTransformationRequest request) throws MindiaException {
    return restClient.createNamedTransformation(request);
  }

  public ReadNamedTransformationsResult readNamedTransformations(ReadNamedTransformationsRequest request) throws MindiaException {
    return restClient.readNamedTransformations();
  }

  public void deleteAllNamedTransformations() throws MindiaException {
    restClient.deleteAllNamedTransformations();
  }
}

