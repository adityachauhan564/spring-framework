package com.gfg.showtime.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/*
 * Sends the "your tickets" email (and pretends to send an SMS).
 * Boot creates a JavaMailSender only when spring.mail.host is set (the "mail" profile points it at
 * Mailpit). ObjectProvider lets this class work either way: with no mail server, the email is only logged.
 */
@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final ObjectProvider<JavaMailSender> mailSender;

    public NotificationService(ObjectProvider<JavaMailSender> mailSender) {
        this.mailSender = mailSender;
    }

    public void send(BookingNotification booking) {
        sendEmail(booking);
        log.info("SMS to {}: {} tickets {} confirmed", booking.mobile(), booking.movieTitle(), booking.seats());
    }

    private void sendEmail(BookingNotification booking) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("tickets@showtime.local");
        mail.setTo(booking.email());
        mail.setSubject("Your ShowTime tickets for " + booking.movieTitle());
        mail.setText("""
                Hi %s,

                %s at %s, %s
                Seats: %s   Amount: %.2f   Ticket: %d
                """.formatted(booking.userName(), booking.movieTitle(), booking.theaterName(), booking.showTime(),
                booking.seats(), booking.amount(), booking.ticketId()));

        JavaMailSender sender = mailSender.getIfAvailable();
        if (sender == null) {
            log.info("No mail server configured, email to {} not sent:\n{}", booking.email(), mail.getText());
            return;
        }
        try {
            sender.send(mail);
            log.info("Email sent to {}", booking.email());
        } catch (MailException e) {
            // the booking is already saved: a mail problem is only logged, never turned into a failed booking
            log.error("Email to {} failed: {}", booking.email(), e.getMessage());
        }
    }
}
