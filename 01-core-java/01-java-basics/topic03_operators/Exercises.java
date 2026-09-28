package topic03_operators;

/*
 * Exercises for topic 03. Replace each "TODO" line with your code, then run:
 *   java -cp out topic03_operators.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. true if n is even. Hint: the remainder operator %.
    static boolean isEven(int n) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. A leap year is divisible by 4, except years divisible by 100, unless also divisible by 400.
    //    2024 and 2000 are leap years; 1900 and 2023 are not. Use one boolean expression with && and ||.
    static boolean isLeapYear(int year) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. The last digit of a number: 1234 -> 4.
    static int lastDigit(int n) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. The average of two ints, as a double: average(3, 4) is 3.5, not 3.
    static double average(int a, int b) {
        throw new UnsupportedOperationException("TODO exercise 4");
    }

    // 5. "adult" if age is 18 or more, otherwise "minor". Use the ternary operator ? :
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

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
