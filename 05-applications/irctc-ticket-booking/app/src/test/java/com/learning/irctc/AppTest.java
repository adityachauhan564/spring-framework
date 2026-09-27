package com.learning.irctc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.learning.irctc.entities.Ticket;
import com.learning.irctc.entities.User;
import com.learning.irctc.services.TrainService;
import com.learning.irctc.services.UserBookingService;
import com.learning.irctc.store.JsonStore;

/*
 * JUnit 5. @TempDir gives each test a fresh empty folder (deleted afterwards), so every test starts
 * from the shipped default data and tests never see each other's bookings.
 */
class AppTest {

    static final LocalDate DAY = LocalDate.of(2030, 1, 15);

    @TempDir Path dataDir;
    JsonStore store;
    UserBookingService bookings;
    TrainService trains;

    @BeforeEach
    void setUp() {
        store = new JsonStore(dataDir);
        bookings = new UserBookingService(store);
        trains = new TrainService(store);
    }

    @Test
    void firstRunCopiesTheDefaultDataAndItParses() {
        assertTrue(Files.exists(dataDir.resolve("users.json")));
        assertEquals(2, store.loadTrains().size());
        assertEquals("aditya", store.loadUsers().get(0).name());       // snake_case JSON -> record fields
    }

    @Test
    void searchRespectsTheDirectionOfTravel() {
        assertEquals("bacs", trains.search("Bangalore", "Delhi").get(0).trainId());   // case doesn't matter
        assertTrue(trains.search("Delhi", "Bangalore").isEmpty());                    // wrong way
        assertTrue(trains.search("Delhi", "Mumbai").isEmpty());
    }

    @Test
    void passwordsAreHashedAndChecked() throws Exception {
        User user = bookings.signUp("ravi", "secret-pass");

        assertTrue(user.hashedPassword().startsWith("$2a$"));
        assertFalse(Files.readString(dataDir.resolve("users.json")).contains("secret-pass"));  // not in the file
        assertTrue(bookings.login("ravi", "secret-pass").isPresent());
        assertTrue(bookings.login("ravi", "wrong").isEmpty());
        assertTrue(bookings.login("aditya", "password123").isPresent());                   // the shipped demo user
        assertThrows(IllegalArgumentException.class, () -> bookings.signUp("RAVI", "another-pass"));
    }

    @Test
    void aBookedSeatIsTakenUntilCancelled() {
        User user = bookings.signUp("meera", "secret-pass");

        Ticket ticket = bookings.book(user.userId(), "dlkn", 0, 1, "delhi", "lucknow", DAY);
        assertThrows(IllegalStateException.class, () -> bookings.book(user.userId(), "dlkn", 0, 1, "delhi", "kanpur", DAY));
        assertEquals(11, trains.findById("dlkn").orElseThrow().freeSeats());

        assertTrue(bookings.cancel(user.userId(), ticket.ticketId()));
        assertEquals(12, trains.findById("dlkn").orElseThrow().freeSeats());
        assertTrue(bookings.tickets(user.userId()).isEmpty());
        assertFalse(bookings.cancel(user.userId(), ticket.ticketId()));                   // already cancelled
    }

    @Test
    void bookingsSurviveARestart() {
        User user = bookings.signUp("kiran", "secret-pass");
        bookings.book(user.userId(), "bacs", 3, 5, "jaipur", "delhi", DAY);

        UserBookingService afterRestart = new UserBookingService(new JsonStore(dataDir));   // same folder, new objects
        Ticket saved = afterRestart.tickets(user.userId()).get(0);
        assertEquals(DAY, saved.dateOfTravel());
        assertEquals(5, saved.seat());
    }

    @Test
    void invalidBookingsAreRejected() {
        User user = bookings.signUp("sam", "secret-pass");
        assertThrows(IllegalArgumentException.class, () -> bookings.book(user.userId(), "dlkn", 0, 0, "lucknow", "delhi", DAY));
        assertThrows(IllegalStateException.class, () -> bookings.book(user.userId(), "dlkn", 9, 9, "delhi", "lucknow", DAY));
        assertThrows(IllegalArgumentException.class, () -> bookings.book(user.userId(), "nope", 0, 0, "delhi", "lucknow", DAY));
    }
}
