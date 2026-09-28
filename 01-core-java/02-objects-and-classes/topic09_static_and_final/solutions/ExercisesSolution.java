package topic09_static_and_final.solutions;

// Solutions for topic09_static_and_final/Exercises.java
public class ExercisesSolution {

    public static void main(String[] args) {
        Ticket a = new Ticket();
        Ticket b = new Ticket();
        Ticket c = new Ticket();
        check(a.getNumber() == 1 && b.getNumber() == 2 && c.getNumber() == 3, "exercise 1 numbering");
        check(Ticket.issued() == 3, "exercise 1 issued()");

        check(MathUtils.square(7) == 49, "exercise 2 square");
        check(MathUtils.circleArea(1) == MathUtils.PI, "exercise 2 circleArea");
        check(MathUtils.PI == 3.14159, "exercise 2 PI constant");

        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

class Ticket {
    private static int lastNumber = 0;    // ONE copy for the whole class: shared by every ticket
    private final int number;             // one copy PER ticket, set once

    Ticket() {
        lastNumber++;
        number = lastNumber;
    }

    int getNumber() {
        return number;
    }

    static int issued() {                 // static: uses only static fields, so it needs no object
        return lastNumber;
    }
}

class MathUtils {
    static final double PI = 3.14159;     // a constant: static + final, UPPER_CASE

    private MathUtils() {
        // a private constructor: nobody needs a MathUtils object, like java.lang.Math
    }

    static int square(int n) {
        return n * n;
    }

    static double circleArea(double radius) {
        return PI * radius * radius;
    }
}
