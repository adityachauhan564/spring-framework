package topic10_exception_basics;

/*
 * Exercises for topic 10. Replace each "TODO" line with your code, then run:
 *   java -cp out topic10_exception_basics.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. Turn text into an int; if it isn't a number, return the fallback instead of crashing.
    //    Integer.parseInt throws NumberFormatException for "abc".
    static int parseOrDefault(String text, int fallback) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Integer division that REJECTS a zero divisor: throw an IllegalArgumentException
    //    with the message "divisor must not be zero".
    static int divide(int a, int b) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Return how many of the texts are valid numbers, using exercise 1's idea (try/catch per item).
    static int countNumbers(String[] texts) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) {
        check(parseOrDefault("42", 0) == 42, "exercise 1");
        check(parseOrDefault("abc", -1) == -1, "exercise 1");

        check(divide(10, 3) == 3, "exercise 2");
        try {
            divide(1, 0);
            check(false, "exercise 2 should throw for a zero divisor");
        } catch (IllegalArgumentException e) {
            check("divisor must not be zero".equals(e.getMessage()), "exercise 2 message");
        }

        check(countNumbers(new String[] {"1", "two", "3", "", "4x"}) == 2, "exercise 3");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
