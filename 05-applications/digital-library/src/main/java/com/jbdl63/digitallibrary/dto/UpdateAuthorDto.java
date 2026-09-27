package com.jbdl63.digitallibrary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Only the fields a client may change: a PUT with the whole entity would also let it rename the author.
public record UpdateAuthorDto(
        @NotNull(message = "Author Id should not be null") Integer authorId,
        @NotBlank(message = "Author Address should not be blank") String address) {
}
