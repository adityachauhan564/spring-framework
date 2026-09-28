package topic02_variables_and_data_types;

/*
 * Exercises for topic 02. Replace each "TODO" line with your code, then run:
 *   java -cp out topic02_variables_and_data_types.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. Convert Celsius to Fahrenheit: F = C * 9 / 5 + 32.
    static double celsiusToFahrenheit(double celsius) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Multiply two ints WITHOUT overflow: 100_000 * 100_000 doesn't fit in an int.
    //    Hint: convert to long BEFORE multiplying. (long) (a * b) is too late - why?
    static long multiply(int a, int b) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Are the two Integer objects the same NUMBER? (== is the wrong tool here)
    static boolean sameNumber(Integer a, Integer b) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Return the whole-number part of a price, dropping the decimals: 19.99 -> 19.
    static int wholePart(double price) {
        throw new UnsupportedOperationException("TODO exercise 4");
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
