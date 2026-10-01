package topic07_methods;

import java.util.Arrays;

/*
 * Exercises for topic 07.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic07_methods.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Return true if n is a prime number (bigger than 1, and divisible only by 1 and itself).
    static boolean isPrime(int n) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. base to the power exp (like 2^10), using RECURSION. No loop, no Math.pow.
    //    Think: what is the base case that stops it?
    static long power(int base, int exp) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Two methods with the same name "area" (overloading):
    //    one for a circle (takes the radius), one for a rectangle (takes width and height).
    static double area(double radius) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    static double area(double width, double height) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Double every value INSIDE the caller's array. Change the array itself, return nothing.
    //    Then look at main: why does this work, when "number = number * 2" on an int parameter would not?
    static void doubleAll(int[] numbers) {
        throw new UnsupportedOperationException("TODO exercise 4");
    }

    // 5. Return the biggest of any number of ints (varargs). There is always at least one.
    static int max(int... values) {
        throw new UnsupportedOperationException("TODO exercise 5");
    }

    public static void main(String[] args) {
        check(isPrime(2) && isPrime(13) && !isPrime(1) && !isPrime(15), "exercise 1");
        check(power(2, 10) == 1024 && power(5, 0) == 1, "exercise 2");
        check(Math.abs(area(1) - Math.PI) < 1e-9, "exercise 3");
        check(area(3, 4) == 12, "exercise 3");
        int[] data = {1, 2, 3};
        doubleAll(data);
        check(Arrays.equals(data, new int[] {2, 4, 6}), "exercise 4");
        check(max(3, 9, 2) == 9 && max(-5) == -5, "exercise 5");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
