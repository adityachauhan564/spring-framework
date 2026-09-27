package com.gfg.showtime.resource;

import java.util.Set;

import com.gfg.showtime.enums.SeatType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

// No userId: the ticket belongs to whoever is logged in. Trusting a userId from the body would let
// anyone book in someone else's name.
public record BookingResource(
        @NotNull(message = "showId is mandatory") Long showId,
        @NotEmpty(message = "seatsNumbers cannot be empty") Set<String> seatsNumbers,
        @NotNull(message = "seatType is mandatory") SeatType seatType) {
}
