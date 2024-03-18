package com.kappann.stockcontrol.error.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GenericException extends RuntimeException {

  private final HttpStatus httpStatus;

  public GenericException(String message, HttpStatus status) {
    super(message);
    this.httpStatus = status;
  }
}
