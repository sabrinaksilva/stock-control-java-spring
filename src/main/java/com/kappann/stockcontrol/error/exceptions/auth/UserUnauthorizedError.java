package com.kappann.stockcontrol.error.exceptions.auth;

public class UserUnauthorizedError extends RuntimeException {

  public UserUnauthorizedError(String message) {
    super(message);
  }

  public UserUnauthorizedError() {
    super("User unauthorized");
  }
}