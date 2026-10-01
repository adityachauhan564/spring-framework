package topic03_operators;

/*
 * Exercises for topic 03.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic03_operators.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Return true if n is even. Hint: the remainder operator %.
    static boolean isEven(int n) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Leap year rule: divisible by 4, BUT not if divisible by 100, UNLESS also divisible by 400.
    //    2024 and 2000 are leap years. 1900 and 2023 are not.
    //    Write it as one true/false expression using && and ||.
    static boolean isLeapYear(int year) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Return the last digit of a number: 1234 -> 4.
    static int lastDigit(int n) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Return the average of two ints as a double: average(3, 4) must be 3.5, not 3.
    static double average(int a, int b) {
        throw new UnsupportedOperationException("TODO exercise 4");
    }

    // 5. Return "adult" if age is 18 or more, otherwise "minor". Use the ternary operator ? :
    static String ageGroup(int age) {
        throw new UnsupportedOperationException("TODO exercise 5");
    }

    public static void main(String[] args) {
        check(isEven(4) && !isEven(7) && isEven(0), "exercise 1");
        check(isLeapYear(2024) && isLeapYear(2000), "exercise 2");
        check(!isLeapYear(1900) && !isLeapYear(2023), "exercise 2");
        check(lastDigit(1234) == 4 && lastDigit(7) == 7, "exercise 3");
        check(average(3, 4) == 3.5, "exercise 4");
        check(ageGroup(18).equals("adult") && ageGroup(12).equals("minor"), "exercise 5");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
