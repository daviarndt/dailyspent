package com.example.dailyspent.utils.exceptions;

public class PhoneNotFoundException extends RuntimeException {

    public PhoneNotFoundException(String message) {
        super(message);
    }

    public PhoneNotFoundException() {
        super("Phone wasn't found using the phoneId provided");
    }
}
