package topic17_records_and_immutability.solutions;

import java.util.Arrays;

// Solutions for topic17_records_and_immutability/Exercises.java
public class ExercisesSolution {

    public static void main(String[] args) {
        Employee e = new Employee(1, "Asha");
        check(e.name().equals("Asha") && e.id() == 1, "exercise 1 accessors");
        check(e.equals(new Employee(1, "Asha")), "exercise 1 equals by value");
        try {
            new Employee(2, " ");
            check(false, "exercise 1 must reject a blank name");
        } catch (IllegalArgumentException expected) {
            // rejected
        }

        Money ten = new Money(10);
        Money fifteen = ten.plus(new Money(5));
        check(fifteen.rupees() == 15 && ten.rupees() == 10, "exercise 2 plus returns a new object");

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

record Employee(int id, String name) {
    Employee {                                  // runs before the fields are assigned
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
    }
}

record Money(int rupees) {
    Money plus(Money other) {
        return new Money(rupees + other.rupees);   // never change this one: make a new one
    }
}

final class Playlist {
    private final String[] songs;

    Playlist(String[] songs) {
        this.songs = songs.clone();
    }

    String[] songs() {
        return songs.clone();
    }
}
