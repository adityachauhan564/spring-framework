package topic09_static_and_final.solutions;

// Answers for topic09_static_and_final/Exercises.java
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

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

class Ticket {
    private static int lastNumber = 0;    // ONE copy for the whole class - the token machine, shared by every ticket
    private final int number;             // one copy PER ticket - the number printed on it, set once

    Ticket() {
        lastNumber++;                     // machine moves to the next number
        number = lastNumber;              // this ticket keeps that number
    }

    int getNumber() {
        return number;
    }

    static int issued() {                 // static: it only uses static fields, so no object is needed
        return lastNumber;
    }
}

class MathUtils {
    static final double PI = 3.14159;     // a constant: static + final, name in CAPITALS

    private MathUtils() {
        // private constructor: stops anyone from writing "new MathUtils()".
        // Nobody needs an object of it - same idea as Java's own Math class
    }

    static int square(int n) {
        return n * n;
    }

    static double circleArea(double radius) {
        return PI * radius * radius;
    }
}
