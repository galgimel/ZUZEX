package com.test_task.zuzex.exception;

public class ApartmentWithoutOwnerException extends Exception {
    public ApartmentWithoutOwnerException() {
    }

    public ApartmentWithoutOwnerException(String message) {
        super(message);
    }
}
