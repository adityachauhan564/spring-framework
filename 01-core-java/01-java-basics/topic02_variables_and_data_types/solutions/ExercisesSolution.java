package topic02_variables_and_data_types.solutions;

// Answers for topic02_variables_and_data_types/Exercises.java
public class ExercisesSolution {

    static double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;        // celsius is a double, so the full calculation is done in double
    }

    static long multiply(int a, int b) {
        return (long) a * b;                // make a long FIRST. (long) (a * b) multiplies as int, overflows, and only then becomes long
    }

    static boolean sameNumber(Integer a, Integer b) {
        return a.equals(b);                 // == checks "same object?" - that gives false for 1000 vs 1000
    }

    static int wholePart(double price) {
        return (int) price;                 // the (int) cast just cuts off the decimals. It does not round
    }

    public static void main(String[] args) {
        check(celsiusToFahrenheit(100) == 212, "exercise 1");
        check(celsiusToFahrenheit(-40) == -40, "exercise 1");
        check(multiply(100_000, 100_000) == 10_000_000_000L, "exercise 2");
        check(sameNumber(1000, 1000), "exercise 3");
        check(!sameNumber(1000, 1001), "exercise 3");
        check(wholePart(19.99) == 19, "exercise 4");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
