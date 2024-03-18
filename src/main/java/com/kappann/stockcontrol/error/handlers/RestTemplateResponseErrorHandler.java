package com.kappann.stockcontrol.error.handlers;


import com.kappann.stockcontrol.error.exceptions.GenericException;
import com.kappann.stockcontrol.error.exceptions.auth.UserForbiddenError;
import com.kappann.stockcontrol.error.exceptions.auth.UserUnauthorizedError;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

@Component

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

  @Override
  public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
    return (httpResponse.getStatusCode().is4xxClientError() || httpResponse.getStatusCode()
        .is5xxServerError());
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    Charset charset = StandardCharsets.UTF_8;

    switch (response.getStatusCode()) {
      case HttpStatus.BAD_REQUEST ->
          throw new GenericException(StreamUtils.copyToString(response.getBody(), charset),
              HttpStatus.BAD_REQUEST);
      case HttpStatus.UNAUTHORIZED -> throw new UserUnauthorizedError("User not logged in");
      case HttpStatus.FORBIDDEN -> throw new UserForbiddenError("User forbidden to perform action");
      case HttpStatus.NOT_FOUND, HttpStatus.INTERNAL_SERVER_ERROR ->
          throw new GenericException("Integration error", HttpStatus.INTERNAL_SERVER_ERROR);
      default -> throw new GenericException("Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
