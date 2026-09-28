package topic17_records_and_immutability;

import java.util.Arrays;

/*
 * Exercises for topic 17. Complete the types below this class, then run:
 *   java -cp out topic17_records_and_immutability.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    public static void main(String[] args) {
        // 1. Employee as a record, rejecting a blank name
        Employee e = new Employee(1, "Asha");
        check(e.name().equals("Asha") && e.id() == 1, "exercise 1 accessors");
        check(e.equals(new Employee(1, "Asha")), "exercise 1 equals by value");
        try {
            new Employee(2, " ");
            check(false, "exercise 1 must reject a blank name");
        } catch (IllegalArgumentException expected) {
            // rejected
        }

        // 2. Money never changes: plus() returns a NEW Money
        Money ten = new Money(10);
        Money fifteen = ten.plus(new Money(5));
        check(fifteen.rupees() == 15 && ten.rupees() == 10, "exercise 2 plus returns a new object");

        // 3. A Playlist that nobody can change from outside
        String[] songs = {"A", "B"};
        Playlist playlist = new Playlist(songs);
        songs[0] = "changed";
        playlist.songs()[1] = "changed";
        check(Arrays.equals(playlist.songs(), new String[] {"A", "B"}), "exercise 3 defensive copies");

        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 1. Turn this into: record Employee(int id, String name) { compact constructor that rejects a blank name }
class Employee {
    Employee(int id, String name) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    int id() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    String name() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }
}

// 2. An immutable amount (a record works well here)
record Money(int rupees) {
    Money plus(Money other) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }
}

// 3. Copy the array on the way in AND on the way out
final class Playlist {
    private final String[] songs;

    Playlist(String[] songs) {
        this.songs = songs;          // TODO exercise 3
    }

    String[] songs() {
        return songs;                // TODO exercise 3
    }
}
