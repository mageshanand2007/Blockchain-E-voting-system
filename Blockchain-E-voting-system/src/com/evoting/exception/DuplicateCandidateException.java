package com.evoting.exception;

// CUSTOM EXCEPTION: thrown when a candidate with the same ID already exists
public class DuplicateCandidateException extends Exception {

    public DuplicateCandidateException(String message) {
        super(message);
    }
}
