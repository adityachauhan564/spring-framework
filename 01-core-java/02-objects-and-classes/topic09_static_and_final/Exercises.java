package topic09_static_and_final;

/*
 * Exercises for topic 09.
 * How to use:
 *   - Complete the classes written BELOW this one. Fill in every "TODO".
 *   - Then run:  java -cp out topic09_static_and_final.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    public static void main(String[] args) {
        // 1. Like token numbers at a bank counter: every new Ticket gets the next number (1, 2, 3, ...).
        //    The counter is shared by ALL tickets.
        Ticket a = new Ticket();
        Ticket b = new Ticket();
        Ticket c = new Ticket();
        check(a.getNumber() == 1 && b.getNumber() == 2 && c.getNumber() == 3, "exercise 1 numbering");
        check(Ticket.issued() == 3, "exercise 1 issued()");

        // 2. Helper methods you call directly on the CLASS - no object needed
        check(MathUtils.square(7) == 49, "exercise 2 square");
        check(MathUtils.circleArea(1) == MathUtils.PI, "exercise 2 circleArea");
        check(MathUtils.PI == 3.14159, "exercise 2 PI constant");

        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 1. Think: which field is shared by all tickets (static), and which one belongs to each ticket?
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

// 2. A utility class (just a box of helper methods): a constant PI = 3.14159,
//    and static methods that don't need any fields.
class MathUtils {
    static final double PI = 0;           // TODO: the right value

    static int square(int n) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    static double circleArea(double radius) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }
}
