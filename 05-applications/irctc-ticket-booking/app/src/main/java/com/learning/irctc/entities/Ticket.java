package com.learning.irctc.entities;

import java.time.LocalDate;

/*
 * A booked seat. It stores the train's id and the seat position, not a copy of the whole train
 * (the course's JSON did that): a copy goes stale as soon as the real train's seats change.
 * row and seat are 0-based here; the console shows them from 1.
 */
public record Ticket(String ticketId, String userId, String trainId, String source, String destination,
                     LocalDate dateOfTravel, int row, int seat) {
}
