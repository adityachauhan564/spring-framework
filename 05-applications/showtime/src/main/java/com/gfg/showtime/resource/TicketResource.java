package com.gfg.showtime.resource;

import java.time.LocalDateTime;
import java.util.Date;

public record TicketResource(
        long id,
        String allottedSeats,
        double amount,
        Date bookedAt,
        long showId,
        String movieTitle,
        String theaterName,
        LocalDateTime showTime) {
}
