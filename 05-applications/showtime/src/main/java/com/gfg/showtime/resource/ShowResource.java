package com.gfg.showtime.resource;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

/*
 * POST /show/add reads showTime, movieId and theaterId. Everything else is filled in only for responses.
 * showTime is plain ISO-8601 JSON ("2026-10-01T18:30:00"). Jackson 3 understands java.time without any setup.
 */
public record ShowResource(
        Long id,               // Long (a wrapper), not long: Jackson 3 rejects a request that leaves out a primitive field
        @NotNull(message = "Show time is mandatory") @Future(message = "Show time must be in the future") LocalDateTime showTime,
        @NotNull(message = "movieId is mandatory") Long movieId,
        @NotNull(message = "theaterId is mandatory") Long theaterId,
        String movieTitle,
        String theaterName,
        String city,
        List<ShowSeatsResource> seats,
        Date createdAt,
        Date updatedAt) {
}
