package com.kappann.stockcontrol.error.handlers;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public class ErrorResponseBuilder {

  public ResponseEntity<ErrorResponse> createResponseEntity(HttpStatus status, String message) {
    return ResponseEntity
        .status(status)
        .body(createResponseException(message, status.value(), LocalDateTime.now()));
  }

  public ErrorResponse createResponseException(String message, Integer statusCode,
      LocalDateTime date) {
    log.error(message);
    return new ErrorResponse(message, statusCode, date);
  }

  public ResponseEntity<ErrorResponse> badRequest(String message) {
    return createResponseEntity(BAD_REQUEST, message);
  }

  public ResponseEntity<ErrorResponse> notFound(String message) {
    return createResponseEntity(NOT_FOUND, message);
  }

  public ResponseEntity<ErrorResponse> unauthorized(String message) {
    return createResponseEntity(UNAUTHORIZED, message);
  }

  public ResponseEntity<ErrorResponse> forbidden(String message) {
    return createResponseEntity(FORBIDDEN, message);
  }

  public ResponseEntity<ErrorResponse> defaultErrorResponse() {
    return createResponseEntity(INTERNAL_SERVER_ERROR, "An unexpected error occur.");
  }
}
