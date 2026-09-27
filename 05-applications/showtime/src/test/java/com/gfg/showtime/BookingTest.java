package com.gfg.showtime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.support.TransactionTemplate;

import com.gfg.showtime.domain.ShowSeat;
import com.gfg.showtime.enums.SeatType;
import com.gfg.showtime.exception.ConflictException;
import com.gfg.showtime.notification.NotificationService;
import com.gfg.showtime.repository.ShowSeatsRepository;
import com.gfg.showtime.resource.BookingResource;
import com.gfg.showtime.resource.TicketResource;
import com.gfg.showtime.service.TicketService;

/*
 * The booking rules, tested on the service (seeded show 1: seats 1A-1E regular, 2A-2E recliners).
 * NotificationService is a mock: the tests check WHEN it's called, without sending anything.
 */
@SpringBootTest(properties = "spring.datasource.url=jdbc:h2:mem:booking-test")
class BookingTest {

    @Autowired TicketService ticketService;
    @Autowired ShowSeatsRepository seats;
    @Autowired TransactionTemplate transaction;
    @MockitoBean NotificationService notificationService;

    @Test
    void bookingMarksTheSeatsAndAddsUpThePrice() {
        TicketResource ticket = book("asha@example.com", SeatType.RECLINER, "2A", "2B");

        assertThat(ticket.amount()).isEqualTo(600);                 // 2 x 300
        assertThat(ticket.allottedSeats()).isEqualTo("2A 2B");
        List<ShowSeat> booked = seats.findAll().stream().filter(s -> s.getTicket() != null && s.getTicket().getId() == ticket.id()).toList();
        assertThat(booked).hasSize(2).allMatch(s -> s.isBooked() && s.getBookedAt() != null);
    }

    @Test
    void theNotificationIsSentAfterACommittedBooking() {
        book("asha@example.com", SeatType.REGULAR, "1E");

        verify(notificationService).send(argThat(n -> n.seats().equals("1E") && n.email().equals("asha@example.com")));
    }

    @Test
    void aFailedBookingSendsNothing() {
        book("asha@example.com", SeatType.REGULAR, "1D");

        assertThrows(ConflictException.class, () -> book("admin@showtime.local", SeatType.REGULAR, "1D"));   // already taken
        assertThrows(ConflictException.class, () -> book("admin@showtime.local", SeatType.RECLINER, "1C"));  // 1C is REGULAR
        verify(notificationService, org.mockito.Mockito.times(1)).send(any());                               // only the first
    }

    // Two customers press "book" for the same seat at the same moment. Whichever way the threads
    // interleave, exactly one gets it: either the second sees the seat already booked (409), or both
    // read it as free and @Version makes the second commit fail (optimistic locking, also 409).
    @Test
    void twoCustomersRacingForOneSeatGetOneTicket() throws Exception {
        CountDownLatch start = new CountDownLatch(1);
        ExecutorService pool = Executors.newFixedThreadPool(2);
        List<Future<TicketResource>> results = List.of("asha@example.com", "admin@showtime.local").stream()
                .map(user -> pool.submit(() -> {
                    start.await();
                    return book(user, SeatType.REGULAR, "1A");
                }))
                .toList();
        start.countDown();

        int tickets = 0;
        for (Future<TicketResource> result : results) {
            try {
                result.get();
                tickets++;
            } catch (java.util.concurrent.ExecutionException e) {
                assertThat(e.getCause()).isInstanceOfAny(ConflictException.class, ObjectOptimisticLockingFailureException.class);
            }
        }
        pool.shutdown();
        assertThat(tickets).isEqualTo(1);
    }

    // The same race, made deterministic: this transaction reads seat 1B, someone else books it and
    // commits, then this transaction writes its stale copy. @Version turns that lost update into an error.
    @Test
    void versionStopsAWriteBasedOnAStaleRead() {
        ShowSeat seat1B = seats.findAll().stream().filter(s -> s.getShow().getId() == 1 && s.getSeatNumber().equals("1B")).findFirst().orElseThrow();

        assertThrows(ObjectOptimisticLockingFailureException.class, () -> transaction.executeWithoutResult(status -> {
            ShowSeat stale = seats.findById(seat1B.getId()).orElseThrow();      // read: version 0, free
            CompletableFuture.runAsync(() -> book("admin@showtime.local", SeatType.REGULAR, "1B")).join();  // another thread commits: version 1
            stale.setBooked(true);                                               // "update ... where version = 0" -> 0 rows
        }));
    }

    @Test
    void noNotificationWithoutABooking() {
        verify(notificationService, never()).send(any());
    }

    private TicketResource book(String email, SeatType type, String... seatNumbers) {
        return ticketService.bookTicket(email, new BookingResource(1L, Set.of(seatNumbers), type));
    }
}
