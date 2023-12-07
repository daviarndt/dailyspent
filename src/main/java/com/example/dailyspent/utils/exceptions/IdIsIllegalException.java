package com.example.dailyspent.utils.exceptions;

public class IdIsIllegalException extends IllegalArgumentException {

    public IdIsIllegalException() {
        super("The ID provided was blank or a negative number");
    }

    public IdIsIllegalException(String message) {
        super(message);
    }
}
