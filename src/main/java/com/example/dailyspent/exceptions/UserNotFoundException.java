package com.example.dailyspent.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User wasn't found using the userId provided");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
