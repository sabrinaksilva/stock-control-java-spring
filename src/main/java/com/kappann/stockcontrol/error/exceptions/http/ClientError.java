package com.kappann.stockcontrol.error.exceptions.http;

import org.springframework.web.client.RestClientException;

public class ClientError extends RestClientException {

  public ClientError(String msg) {
    super(msg);
  }

  public ClientError(String msg, Throwable ex) {
    super(msg, ex);
  }
}