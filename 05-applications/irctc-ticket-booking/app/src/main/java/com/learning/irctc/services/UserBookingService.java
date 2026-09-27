package com.learning.irctc.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

import com.learning.irctc.entities.Ticket;
import com.learning.irctc.entities.Train;
import com.learning.irctc.entities.User;
import com.learning.irctc.store.JsonStore;

/*
 * Sign up, log in, book, cancel: what Spring gave you for free in the other projects, by hand.
 *   - no @Service / dependency injection: App creates the objects and passes them in
 *   - no @Transactional: a booking changes two files (trains.json, users.json) and there is nothing
 *     to undo the first write if the second fails. A database transaction would.
 *   - no Spring Security: passwords are hashed and checked with BCrypt directly
 * ponytail: seats belong to the train, not to a date - booking 1A on Monday also takes it on Tuesday.
 *   A real system stores one seat map per train per travel date.
 */
public class UserBookingService {

    private final JsonStore store;

    public UserBookingService(JsonStore store) {
        this.store = store;
    }

    public User signUp(String name, String password) {
        if (name.isBlank() || password.length() < 6) {
            throw new IllegalArgumentException("Name is required and the password needs at least 6 characters");
        }
        List<User> users = store.loadUsers();
        if (users.stream().anyMatch(u -> u.name().equalsIgnoreCase(name))) {
            throw new IllegalArgumentException("User name already taken: " + name);
        }
        // hashpw adds a random salt, so two users with the same password get different hashes
        User user = new User(UUID.randomUUID().toString(), name, BCrypt.hashpw(password, BCrypt.gensalt()), new ArrayList<>());
        users.add(user);
        store.saveUsers(users);
        return user;
    }

    public Optional<User> login(String name, String password) {
        return store.loadUsers().stream()
                .filter(u -> u.name().equalsIgnoreCase(name))
                .filter(u -> BCrypt.checkpw(password, u.hashedPassword()))   // re-hashes with the stored salt and compares
                .findFirst();
    }

    public Ticket book(String userId, String trainId, int row, int seat, String source, String destination, LocalDate date) {
        List<Train> trains = store.loadTrains();
        Train train = trains.stream().filter(t -> t.trainId().equals(trainId)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No train " + trainId));
        if (!train.runsBetween(source, destination)) {
            throw new IllegalArgumentException(trainId + " doesn't run from " + source + " to " + destination);
        }
        if (!train.isFree(row, seat)) {
            throw new IllegalStateException("Seat " + (row + 1) + "-" + (seat + 1) + " is taken or doesn't exist");
        }

        List<User> users = store.loadUsers();
        User user = find(users, userId);
        Ticket ticket = new Ticket(UUID.randomUUID().toString(), userId, trainId, source, destination, date, row, seat);

        train.seats().get(row).set(seat, Train.BOOKED);
        user.ticketsBooked().add(ticket);
        store.saveTrains(trains);
        store.saveUsers(users);
        return ticket;
    }

    public boolean cancel(String userId, String ticketId) {
        List<User> users = store.loadUsers();
        User user = find(users, userId);
        Optional<Ticket> ticket = user.ticketsBooked().stream().filter(t -> t.ticketId().equals(ticketId)).findFirst();
        if (ticket.isEmpty()) return false;

        List<Train> trains = store.loadTrains();
        trains.stream().filter(t -> t.trainId().equals(ticket.get().trainId())).findFirst()
                .ifPresent(train -> train.seats().get(ticket.get().row()).set(ticket.get().seat(), Train.FREE));
        user.ticketsBooked().remove(ticket.get());
        store.saveTrains(trains);
        store.saveUsers(users);
        return true;
    }

    public List<Ticket> tickets(String userId) {
        return List.copyOf(find(store.loadUsers(), userId).ticketsBooked());
    }

    private static User find(List<User> users, String userId) {
        return users.stream().filter(u -> u.userId().equals(userId)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No user " + userId));
    }
}
