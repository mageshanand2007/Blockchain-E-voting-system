package com.evoting.exception;

// CUSTOM EXCEPTION: thrown when admin username/password is incorrect
public class InvalidCredentialsException extends Exception {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
