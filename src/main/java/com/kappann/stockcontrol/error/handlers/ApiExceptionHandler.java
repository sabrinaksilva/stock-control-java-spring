package com.kappann.stockcontrol.error.handlers;


import com.kappann.stockcontrol.error.exceptions.GenericException;
import com.kappann.stockcontrol.error.exceptions.auth.UserForbiddenError;
import com.kappann.stockcontrol.error.exceptions.auth.UserUnauthorizedError;
import com.kappann.stockcontrol.error.exceptions.data.DataAccessError;
import com.kappann.stockcontrol.error.exceptions.data.IntegrityViolationError;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    private final ErrorResponseBuilder responseBuilder = new ErrorResponseBuilder();

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedError(Throwable ex) {
        log.error("Internal server error:", ex);
        return responseBuilder.defaultErrorResponse();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception) {
        List<String> fieldErrors = new ArrayList<>();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> fieldErrors.add(error.getDefaultMessage()));

        StringJoiner sj = new StringJoiner("; \n", "", "");
        fieldErrors.forEach(sj::add);

        return responseBuilder.badRequest(sj.toString());
    }

    @ExceptionHandler(UserUnauthorizedError.class)
    public ResponseEntity<ErrorResponse> handleUserUnauthorizedError(UserUnauthorizedError exception) {
        return responseBuilder.unauthorized(exception.getMessage());
    }

    @ExceptionHandler(UserForbiddenError.class)
    public ResponseEntity<ErrorResponse> handleUserForbiddenErrorError(UserForbiddenError exception) {
        return responseBuilder.forbidden(exception.getMessage());
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPayloadError(HttpMessageNotReadableException exception) {
        return responseBuilder.badRequest("Invalid value received.");
    }

    @ExceptionHandler(IntegrityViolationError.class)
    public ResponseEntity<ErrorResponse> handleIntegrityViolationError(IntegrityViolationError exception) {
        return responseBuilder.badRequest(exception.getMessage());
    }

    @ExceptionHandler(DataAccessError.class)
    public ResponseEntity<ErrorResponse> handleDataAccessError(DataAccessError exception) {
        return responseBuilder.createResponseEntity(exception.getHttpStatus(), exception.getMessage());
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(GenericException exception) {
        return responseBuilder.createResponseEntity(exception.getHttpStatus(), exception.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFoundException(EntityNotFoundException exception) {
        return responseBuilder.notFound(exception.getMessage());
    }


    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErrorResponse> handleEntityExistsException(EntityExistsException exception) {
        return responseBuilder.badRequest(exception.getMessage());
    }

}
