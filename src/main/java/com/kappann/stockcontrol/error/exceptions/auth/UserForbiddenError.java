package com.kappann.stockcontrol.error.exceptions.auth;

public class UserForbiddenError extends RuntimeException {

  public UserForbiddenError(String message) {
    super(message);
  }

  public UserForbiddenError() {
    super("User forbidden");
  }

}