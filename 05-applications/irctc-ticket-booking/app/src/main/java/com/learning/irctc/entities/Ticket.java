package com.learning.irctc.entities;

import java.time.LocalDate;

/*
 * A booked seat. It stores only the train's id and the seat position, not a copy of the whole train
 * (the course's JSON did that). A copy becomes out of date as soon as the real train's seats change -
 * like a photo of a notice board that someone has since updated.
 * row and seat start from 0 here; the console shows them starting from 1.
 */
public record Ticket(String ticketId, String userId, String trainId, String source, String destination,
                     LocalDate dateOfTravel, int row, int seat) {
}
