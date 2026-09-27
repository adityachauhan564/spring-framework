package com.gfg.showtime.exception;

// Something the request refers to doesn't exist -> 404
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
