package topic04_control_flow;

/*
 * Exercises for topic 04. Replace each "TODO" line with your code, then run:
 *   java -cp out topic04_control_flow.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. FizzBuzz for one number: "Fizz" if divisible by 3, "Buzz" if by 5, "FizzBuzz" if by both,
    //    otherwise the number itself as text. Which check has to come first?
    static String fizzBuzz(int n) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. The multiplication table of n as one line, from 1 to 5:  for 3 -> "3 6 9 12 15"
    //    (numbers separated by one space, no space at the end).
    static String timesTable(int n) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. The sum of the digits of a positive number: 1234 -> 10. Use a while loop with % and /.
    static int digitSum(int n) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Days in a month (1-12) of a non-leap year, with a switch expression. Return 0 for an invalid month.
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

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
