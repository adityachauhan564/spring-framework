package com.gfg.showtime.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

/*
 * Used only with the "kafka" profile. Kafka = a message queue: one part of the app drops a message,
 * another part picks it up later. Like a post box - the sender doesn't wait for the receiver.
 * But unlike a post box, Kafka KEEPS the message after it is read, so another group can read it too.
 * The producer, consumer and KafkaTemplate need no code: Boot builds them from the spring.kafka.*
 * properties (application-kafka.properties). The course built them by hand, with localhost:9092 hard-coded.
 * The only thing left is the topic: this bean makes Boot's KafkaAdmin create it if it doesn't exist yet.
 */
@Configuration
@Profile("kafka")
public class KafkaConfig {

    public static final String TICKET_BOOKED = "TICKET_BOOKED";

    @Bean
    public NewTopic ticketBookedTopic() {
        return TopicBuilder.name(TICKET_BOOKED).partitions(1).replicas(1).build();
    }
}
