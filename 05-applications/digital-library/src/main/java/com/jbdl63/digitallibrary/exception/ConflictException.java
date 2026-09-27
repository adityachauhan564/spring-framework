package com.jbdl63.digitallibrary.exception;

// The request is valid but clashes with the current state (a duplicate, still in use...) -> 409
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
