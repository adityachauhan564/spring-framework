package topic17_records_and_immutability;

import java.util.Arrays;

/*
 * Exercises for topic 17.
 * How to use:
 *   - Complete the types written BELOW this class. Fill in every "TODO".
 *   - Then run:  java -cp out topic17_records_and_immutability.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    public static void main(String[] args) {
        // 1. Employee as a record, which refuses a blank name
        Employee e = new Employee(1, "Asha");
        check(e.name().equals("Asha") && e.id() == 1, "exercise 1 accessors");
        check(e.equals(new Employee(1, "Asha")), "exercise 1 equals by value");
        try {
            new Employee(2, " ");
            check(false, "exercise 1 must reject a blank name");
        } catch (IllegalArgumentException expected) {
            // refused - correct
        }

        // 2. Money never changes. plus() must give back a NEW Money object
        Money ten = new Money(10);
        Money fifteen = ten.plus(new Money(5));
        check(fifteen.rupees() == 15 && ten.rupees() == 10, "exercise 2 plus returns a new object");

        // 3. A Playlist that nobody can change from outside
        String[] songs = {"A", "B"};
        Playlist playlist = new Playlist(songs);
        songs[0] = "changed";                   // change the array we passed in
        playlist.songs()[1] = "changed";        // change the array we got back
        check(Arrays.equals(playlist.songs(), new String[] {"A", "B"}), "exercise 3 defensive copies");

        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 1. Turn this class into:  record Employee(int id, String name) { ... }
//    with a compact constructor that refuses a blank name.
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

// 2. An amount of money that can never change (a record is a good fit for this)
record Money(int rupees) {
    Money plus(Money other) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }
}

// 3. Make a copy of the array when it comes IN, and also when it goes OUT
final class Playlist {
    private final String[] songs;

    Playlist(String[] songs) {
        this.songs = songs;          // TODO exercise 3
    }

    String[] songs() {
        return songs;                // TODO exercise 3
    }
}
