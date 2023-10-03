package com.conference.system;

public class ValidationException extends IllegalArgumentException {
    public ValidationException(Throwable throwable) {
        super(throwable);
    }

    public ValidationException(String message) {
        super(message);
    }
}
