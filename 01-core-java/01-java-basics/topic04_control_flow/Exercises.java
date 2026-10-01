package topic04_control_flow;

/*
 * Exercises for topic 04.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic04_control_flow.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. FizzBuzz for one number:
    //      divisible by 3        -> "Fizz"
    //      divisible by 5        -> "Buzz"
    //      divisible by both     -> "FizzBuzz"
    //      otherwise             -> the number itself, as text
    //    Think: which check must come first?
    static String fizzBuzz(int n) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. The table (pahada) of n from 1 to 5, in one line. For 3 -> "3 6 9 12 15"
    //    (one space between numbers, no space at the end).
    static String timesTable(int n) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Add up the digits of a positive number: 1234 -> 1 + 2 + 3 + 4 = 10.
    //    Use a while loop with % and /.
    static int digitSum(int n) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Number of days in a month (1 to 12), for a non-leap year. Use a switch expression.
    //    Return 0 if the month number is wrong.
    static int daysInMonth(int month) {
        throw new UnsupportedOperationException("TODO exercise 4");
    }

    public static void main(String[] args) {
        check(fizzBuzz(15).equals("FizzBuzz") && fizzBuzz(9).equals("Fizz"), "exercise 1");
        check(fizzBuzz(10).equals("Buzz") && fizzBuzz(7).equals("7"), "exercise 1");
        check(timesTable(3).equals("3 6 9 12 15"), "exercise 2");
        check(timesTable(1).equals("1 2 3 4 5"), "exercise 2");
        check(digitSum(1234) == 10 && digitSum(7) == 7, "exercise 3");
        check(daysInMonth(2) == 28 && daysInMonth(4) == 30 && daysInMonth(12) == 31, "exercise 4");
        check(daysInMonth(13) == 0, "exercise 4");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
