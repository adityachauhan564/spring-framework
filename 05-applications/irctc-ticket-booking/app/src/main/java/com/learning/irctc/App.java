package com.learning.irctc;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.learning.irctc.entities.Ticket;
import com.learning.irctc.entities.Train;
import com.learning.irctc.entities.User;
import com.learning.irctc.services.TrainService;
import com.learning.irctc.services.UserBookingService;
import com.learning.irctc.store.JsonStore;

/*
 * The console menu. It only reads input and prints results; every rule lives in the services.
 * Here "wiring" is two constructor calls, which Spring's dependency injection does in the other projects.
 * Data is kept in ./data (created on the first run, next to where you start the app).
 */
public class App {

    private final Scanner in = new Scanner(System.in);
    private final UserBookingService bookings;
    private final TrainService trains;
    private User user;                                   // null until someone logs in

    App(JsonStore store) {
        this.bookings = new UserBookingService(store);
        this.trains = new TrainService(store);
    }

    public static void main(String[] args) {
        new App(new JsonStore(Path.of("data"))).run();
    }

    void run() {
        System.out.println("IRCTC ticket booking (data in " + Path.of("data").toAbsolutePath() + ")");
        while (true) {
            System.out.println("""

                    1 Sign up   2 Log in   3 Search trains   4 Book a seat
                    5 My bookings   6 Cancel a booking   0 Exit""");
            String choice = ask("Choose");
            try {
                switch (choice) {
                    case "1" -> signUp();
                    case "2" -> logIn();
                    case "3" -> search();
                    case "4" -> book();
                    case "5" -> showBookings();
                    case "6" -> cancel();
                    case "0" -> { return; }
                    default -> System.out.println("Unknown option");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("! " + e.getMessage());          // a rule was broken: say why, keep the menu running
            }
        }
    }

    private void signUp() {
        user = bookings.signUp(ask("Name"), ask("Password"));
        System.out.println("Welcome, " + user.name() + " (you are logged in)");
    }

    private void logIn() {
        Optional<User> found = bookings.login(ask("Name"), ask("Password"));
        found.ifPresentOrElse(u -> System.out.println("Logged in as " + (user = u).name()),
                () -> System.out.println("! Wrong name or password"));  // never say which one was wrong
    }

    private void search() {
        String from = ask("From"), to = ask("To");
        List<Train> found = trains.search(from, to);
        if (found.isEmpty()) System.out.println("No train from " + from + " to " + to);
        for (Train train : found) {
            System.out.printf("%s (no. %s)  %s  departs %s at %s  free seats: %d%n", train.trainId(), train.trainNo(),
                    String.join(" -> ", train.stations()), from, train.stationTimes().get(from.toLowerCase()), train.freeSeats());
        }
    }

    private void book() {
        requireLogin();
        String from = ask("From"), to = ask("To");
        String trainId = ask("Train id");
        Train train = trains.findById(trainId).orElseThrow(() -> new IllegalArgumentException("No train " + trainId));
        printSeats(train);
        int row = Integer.parseInt(ask("Row")) - 1;               // people count from 1, lists from 0
        int seat = Integer.parseInt(ask("Seat")) - 1;
        Ticket ticket = bookings.book(user.userId(), train.trainId(), row, seat, from, to, date(ask("Date (yyyy-mm-dd)")));
        System.out.println("Booked! Ticket " + ticket.ticketId());
    }

    private void showBookings() {
        requireLogin();
        List<Ticket> tickets = bookings.tickets(user.userId());
        if (tickets.isEmpty()) System.out.println("No bookings yet");
        for (Ticket t : tickets) {
            System.out.printf("%s  %s %s -> %s on %s, seat %d-%d%n", t.ticketId(), t.trainId(), t.source(), t.destination(),
                    t.dateOfTravel(), t.row() + 1, t.seat() + 1);
        }
    }

    private void cancel() {
        requireLogin();
        System.out.println(bookings.cancel(user.userId(), ask("Ticket id")) ? "Cancelled" : "! No such ticket");
    }

    private void printSeats(Train train) {
        System.out.println("Seats (. free, X booked):");
        for (int r = 0; r < train.seats().size(); r++) {
            StringBuilder line = new StringBuilder("  row " + (r + 1) + "  ");
            for (int s = 0; s < train.seats().get(r).size(); s++) line.append(train.isFree(r, s) ? ". " : "X ");
            System.out.println(line);
        }
    }

    private void requireLogin() {
        if (user == null) throw new IllegalStateException("Log in first (option 2)");
    }

    private static LocalDate date(String text) {
        try {
            return LocalDate.parse(text);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Not a date: " + text);
        }
    }

    private String ask(String prompt) {
        System.out.print(prompt + ": ");
        if (!in.hasNextLine()) System.exit(0);                  // input closed (Ctrl+D / end of a piped file)
        return in.nextLine().trim();
    }
}
