package topic02_variables_and_data_types.solutions;

// Solutions for topic02_variables_and_data_types/Exercises.java
public class ExercisesSolution {

    static double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;        // celsius is a double, so the whole expression is double
    }

    static long multiply(int a, int b) {
        return (long) a * b;                // widen first; (long) (a * b) overflows as an int, then widens
    }

    static boolean sameNumber(Integer a, Integer b) {
        return a.equals(b);                 // == compares objects: false for 1000 vs 1000
    }

    static int wholePart(double price) {
        return (int) price;                 // a narrowing cast cuts off the decimals, it doesn't round
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

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
