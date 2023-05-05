package com.test_task.zuzex.exception;

public class UserWithoutApartmentException extends Exception {
    public UserWithoutApartmentException() {
    }

    public UserWithoutApartmentException(String message) {
        super(message);
    }

    public UserWithoutApartmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserWithoutApartmentException(Throwable cause) {
        super(cause);
    }

    public UserWithoutApartmentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
