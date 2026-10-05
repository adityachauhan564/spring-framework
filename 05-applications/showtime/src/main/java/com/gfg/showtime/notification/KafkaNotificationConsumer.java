package com.gfg.showtime.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.gfg.showtime.config.KafkaConfig;

import tools.jackson.databind.json.JsonMapper;

/*
 * "kafka" profile: reads TICKET_BOOKED messages and sends the notifications.
 * groupId: consumers in the same group share the messages (each message is handled only once per group),
 * like delivery partners from one restaurant sharing the orders between them.
 */
@Component
@Profile("kafka")
public class KafkaNotificationConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaNotificationConsumer.class);

    private final NotificationService notificationService;
    private final JsonMapper jsonMapper;

    public KafkaNotificationConsumer(NotificationService notificationService, JsonMapper jsonMapper) {
        this.notificationService = notificationService;
        this.jsonMapper = jsonMapper;
    }

    @KafkaListener(topics = KafkaConfig.TICKET_BOOKED, groupId = "ticketGroup")
    public void onMessage(String message) {
        log.info("Received from {}: {}", KafkaConfig.TICKET_BOOKED, message);
        notificationService.send(jsonMapper.readValue(message, BookingNotification.class));
    }
}
