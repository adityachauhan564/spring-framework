package com.gfg.showtime.exception;

// The request is valid, but clashes with the current data (a duplicate, a seat already booked) -> 409
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
