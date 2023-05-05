package com.test_task.zuzex.exception;

public class UserWithoutApartmentException extends Exception {
    public UserWithoutApartmentException() {
    }

    public UserWithoutApartmentException(String message) {
        super(message);
    }
}
