package com.gfg.showtime.notification;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/*
 * Default (no "kafka" profile): the booking event is handled inside this same application.
 * @TransactionalEventListener runs only AFTER the booking transaction commits (phase AFTER_COMMIT
 * by default). If the booking rolls back, nobody gets an email for a ticket that doesn't exist.
 * A plain @EventListener would run straight away, inside the transaction, before we know if it succeeds.
 */
@Component
@Profile("!kafka")
public class LocalNotificationListener {

    private final NotificationService notificationService;

    public LocalNotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @TransactionalEventListener
    public void onBooking(BookingNotification booking) {
        notificationService.send(booking);
    }
}
