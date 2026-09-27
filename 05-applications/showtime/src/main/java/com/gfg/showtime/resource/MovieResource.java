package com.gfg.showtime.resource;

import java.util.List;

import com.gfg.showtime.enums.Genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Request body for POST /movie/add (only title + genre are read) and the response for every movie endpoint
public record MovieResource(
        Long id,               // a wrapper, not long: Jackson 3 rejects a request that omits a primitive field
        @NotBlank(message = "Title is mandatory") String title,
        @NotNull(message = "Genre is mandatory") Genre genre,
        Double rating,
        List<ReviewResource> reviews) {
}
