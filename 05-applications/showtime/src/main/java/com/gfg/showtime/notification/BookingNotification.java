package com.gfg.showtime.notification;

import java.time.LocalDateTime;

/*
 * What a booking notification needs, as plain values. This is the Kafka message (as JSON) and the
 * Spring event. The course put JPA entities (Show, ShowSeat) in the message: that ties the message
 * format to the database model and serializes far more than the email needs.
 */
public record BookingNotification(
        long ticketId,
        String userName,
        String email,
        String mobile,
        String movieTitle,
        String theaterName,
        LocalDateTime showTime,
        String seats,
        double amount) {
}
