package com.gfg.showtime.exception;

// Something the request asks for does not exist -> 404
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
