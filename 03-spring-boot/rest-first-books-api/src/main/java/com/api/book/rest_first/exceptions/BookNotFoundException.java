package com.api.book.rest_first.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(int id) {
        super("No book with id " + id);
    }
}
