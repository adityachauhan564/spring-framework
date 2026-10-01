package topic10_exception_basics;

/*
 * Exercises for topic 10.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic10_exception_basics.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Turn text into an int. If the text is not a number, return the fallback value instead of crashing.
    //    Note: Integer.parseInt("abc") throws a NumberFormatException.
    static int parseOrDefault(String text, int fallback) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Divide a by b, but REFUSE a zero divisor: throw an IllegalArgumentException
    //    with exactly this message: "divisor must not be zero".
    static int divide(int a, int b) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Count how many of the texts are valid numbers. Use the idea from exercise 1
    //    (a try/catch for each item), so one bad item doesn't stop the whole count.
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

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
