package topic02_variables_and_data_types;

/*
 * Exercises for topic 02.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic02_variables_and_data_types.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Change Celsius to Fahrenheit using this formula: F = C * 9 / 5 + 32
    //    (Delhi in summer, 45 C, is 113 F.)
    static double celsiusToFahrenheit(double celsius) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Multiply two ints WITHOUT overflow. 100_000 * 100_000 is too big to fit in an int.
    //    Hint: change to long BEFORE you multiply. Writing (long) (a * b) is too late - can you see why?
    static long multiply(int a, int b) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Do the two Integer objects hold the same NUMBER? (== is the wrong tool for this)
    static boolean sameNumber(Integer a, Integer b) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Return only the rupees part of a price, drop the paise: 19.99 -> 19.
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

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
