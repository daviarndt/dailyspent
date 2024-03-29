package com.example.dailyspent.utils.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("User already exists for the given email address");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
