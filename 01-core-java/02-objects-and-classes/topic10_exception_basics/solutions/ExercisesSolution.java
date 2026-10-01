package topic10_exception_basics.solutions;

// Answers for topic10_exception_basics/Exercises.java
public class ExercisesSolution {

    static int parseOrDefault(String text, int fallback) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {         // catch only the problem you expect, not every Exception
            return fallback;
        }
    }

    static int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("divisor must not be zero");     // stop bad input at the door
        }
        return a / b;
    }

    static int countNumbers(String[] texts) {
        int count = 0;
        for (String text : texts) {
            try {
                Integer.parseInt(text);             // if this works, it was a number
                count++;
            } catch (NumberFormatException e) {
                // not a number: skip it and carry on with the rest
            }
        }
        return count;
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
