package com.gfg.showtime.resource;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// A record has a constructor with every field, which Jackson uses to read JSON: no setters needed.
// (The course's Lombok class had no no-args constructor, so POST /review/add couldn't read its body.)
public record ReviewResource(
        Long id,
        @NotNull(message = "movieId is mandatory") Long movieId,
        @NotBlank(message = "Review text is mandatory") String movieReview,
        @DecimalMin(value = "1", message = "Rating is 1 to 5") @DecimalMax(value = "5", message = "Rating is 1 to 5") @NotNull(message = "Rating is mandatory") Double rating) {
}
