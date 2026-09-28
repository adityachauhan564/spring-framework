package topic09_static_and_final;

/*
 * Exercises for topic 09. Complete the classes below this one, then run:
 *   java -cp out topic09_static_and_final.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    public static void main(String[] args) {
        // 1. Every new Ticket gets the next number (1, 2, 3, ...), shared across ALL tickets
        Ticket a = new Ticket();
        Ticket b = new Ticket();
        Ticket c = new Ticket();
        check(a.getNumber() == 1 && b.getNumber() == 2 && c.getNumber() == 3, "exercise 1 numbering");
        check(Ticket.issued() == 3, "exercise 1 issued()");

        // 2. Utility methods you call on the CLASS, no object needed
        check(MathUtils.square(7) == 49, "exercise 2 square");
        check(MathUtils.circleArea(1) == MathUtils.PI, "exercise 2 circleArea");
        check(MathUtils.PI == 3.14159, "exercise 2 PI constant");

        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 1. Which field is shared by all tickets (static), and which belongs to each ticket?
class Ticket {
    // TODO: a static counter, and a final number for this ticket

    Ticket() {
        // TODO: take the next number
    }

    int getNumber() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    static int issued() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }
}

// 2. A utility class: a constant PI = 3.14159, and static methods that use no fields.
class MathUtils {
    static final double PI = 0;           // TODO: the right value

    static int square(int n) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    static double circleArea(double radius) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }
}
