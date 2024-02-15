package com.kappann.stockcontrol.error.exceptions.data;

public class IntegrityViolationError extends RuntimeException {
    public IntegrityViolationError(String message) {
        super(message);
    }
}