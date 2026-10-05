package com.api.book.rest_first.dto;

import com.api.book.rest_first.entities.Book;

/*
 * DTO layer: what the API SENDS back. It is kept separate from the entity, so:
 *   - the database design can change without breaking clients, and
 *   - internal fields can never leak out by mistake.
 */
public record BookResponse(int id, String title, String author) {

    public static BookResponse from(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor());
    }
}
