package org.mindia;

import okhttp3.OkHttpClient;
import org.mindia.exceptions.MindiaException;
import org.mindia.handlers.NamedTransformationsHandler;
import org.mindia.models.requests.CreateNamedTransformationRequest;
import org.mindia.models.requests.ReadNamedTransformationsRequest;
import org.mindia.models.results.CreateNamedTransformationResult;
import org.mindia.models.results.ReadNamedTransformationsResult;

public class Client {
  private final NamedTransformationsHandler namedTransformationsHandler;

  public Client(Config config) {
    OkHttpClient client = new OkHttpClient();
    namedTransformationsHandler = new NamedTransformationsHandler(client, config);
  }

  public CreateNamedTransformationResult createNamedTransformation(CreateNamedTransformationRequest request) throws MindiaException {
    return namedTransformationsHandler.createNamedTransformation(request);
  }

  public ReadNamedTransformationsResult readNamedTransformations(ReadNamedTransformationsRequest request) throws MindiaException {
    return namedTransformationsHandler.readNamedTransformations();
  }

  public void deleteAllNamedTransformations() throws MindiaException {
    namedTransformationsHandler.deleteAllNamedTransformations();
  }
}

