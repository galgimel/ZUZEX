package com.test_task.zuzex.exception;

public class ApartmentWithoutOwnerException extends Exception {
    public ApartmentWithoutOwnerException() {
    }

    public ApartmentWithoutOwnerException(String message) {
        super(message);
    }

    public ApartmentWithoutOwnerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApartmentWithoutOwnerException(Throwable cause) {
        super(cause);
    }

    public ApartmentWithoutOwnerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
