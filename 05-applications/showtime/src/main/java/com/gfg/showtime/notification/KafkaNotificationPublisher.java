package com.gfg.showtime.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.gfg.showtime.config.KafkaConfig;

import tools.jackson.databind.json.JsonMapper;

/*
 * "kafka" profile: after the booking commits, publish it to the TICKET_BOOKED topic as JSON.
 * Whoever sends the email (here KafkaNotificationConsumer; in a real system, another service) reads it
 * from there. The booking request doesn't wait for the email, and still works while the mail side is down:
 * Kafka keeps the message until a consumer takes it.
 */
@Component
@Profile("kafka")
public class KafkaNotificationPublisher {

    private static final Logger log = LoggerFactory.getLogger(KafkaNotificationPublisher.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final JsonMapper jsonMapper;          // Boot 4 = Jackson 3: tools.jackson, not com.fasterxml.jackson

    public KafkaNotificationPublisher(KafkaTemplate<String, String> kafkaTemplate, JsonMapper jsonMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.jsonMapper = jsonMapper;
    }

    @TransactionalEventListener
    public void onBooking(BookingNotification booking) {
        String json = jsonMapper.writeValueAsString(booking);
        // key = ticket id: messages with the same key always go to the same partition, in order
        kafkaTemplate.send(KafkaConfig.TICKET_BOOKED, String.valueOf(booking.ticketId()), json);
        log.info("Published to {}: {}", KafkaConfig.TICKET_BOOKED, json);
    }
}
