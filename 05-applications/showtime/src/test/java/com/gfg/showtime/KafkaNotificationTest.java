package com.gfg.showtime;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.gfg.showtime.config.KafkaConfig;
import com.gfg.showtime.enums.SeatType;
import com.gfg.showtime.notification.NotificationService;
import com.gfg.showtime.resource.BookingResource;
import com.gfg.showtime.service.TicketService;

/*
 * The "kafka" profile end to end, with a real Kafka broker started inside the test (@EmbeddedKafka):
 * book -> KafkaNotificationPublisher -> topic TICKET_BOOKED -> KafkaNotificationConsumer -> NotificationService.
 * The consumer runs on its own thread, so the check waits (timeout) instead of expecting it at once.
 */
@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:kafka-test",
        "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}" })
@ActiveProfiles("kafka")
@EmbeddedKafka(partitions = 1, topics = KafkaConfig.TICKET_BOOKED)
class KafkaNotificationTest {

    @Autowired TicketService ticketService;
    @MockitoBean NotificationService notificationService;

    @Test
    void aBookingTravelsThroughKafkaToTheNotificationService() {
        ticketService.bookTicket("asha@example.com", new BookingResource(1L, Set.of("2E"), SeatType.RECLINER));

        verify(notificationService, timeout(20_000))
                .send(argThat(n -> n.seats().equals("2E") && n.movieTitle().equals("Inception") && n.amount() == 300));
    }
}
