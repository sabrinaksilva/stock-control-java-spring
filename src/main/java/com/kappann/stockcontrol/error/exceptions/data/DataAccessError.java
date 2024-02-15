package com.kappann.stockcontrol.error.exceptions.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DataAccessError extends RuntimeException {
    private final HttpStatus httpStatus;

    public DataAccessError(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}