package topic07_methods.solutions;

import java.util.Arrays;

// Answers for topic07_methods/Exercises.java
public class ExercisesSolution {

    static boolean isPrime(int n) {
        if (n < 2) {
            return false;                       // 0, 1 and negative numbers are not prime
        }
        // we only need to check up to the square root of n:
        // if 36 = 4 x 9, then 4 (the smaller one) is found before we ever reach 9
        for (int d = 2; d * d <= n; d++) {
            if (n % d == 0) {
                return false;                   // found a divisor, so not prime
            }
        }
        return true;
    }

    static long power(int base, int exp) {
        if (exp == 0) {
            return 1;                           // base case: anything to the power 0 is 1. This stops the recursion
        }
        return base * power(base, exp - 1);     // 2^10 = 2 * 2^9. Every call gets one step closer to exp == 0
    }

    static double area(double radius) {
        return Math.PI * radius * radius;       // circle: pi * r * r
    }

    static double area(double width, double height) {
        return width * height;                  // rectangle: width * height
    }

    static void doubleAll(int[] numbers) {
        // numbers is a copy of the ADDRESS, so it points to the caller's own array.
        // Changing numbers[i] changes the caller's array also.
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] *= 2;
        }
    }

    static int max(int... values) {
        int best = values[0];                   // varargs come in as an array, so values[0] is the first one
        for (int v : values) {
            best = Math.max(best, v);
        }
        return best;
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
