package com.api.book.rest_first.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
 * DTO layer: what a client SENDS to create or replace a book.
 * No id (the database assigns it) - so a client can't overwrite another book by sending an id.
 */
public record BookRequest(
        @NotBlank(message = "title is required") @Size(max = 200, message = "title is at most 200 characters") String title,
        @NotBlank(message = "author is required") @Size(max = 100, message = "author is at most 100 characters") String author) {
}
