package topic13_methods_and_static;

import java.util.Arrays;

/*
 * Topic    : Methods - parameters, return values, overloading, varargs, recursion, pass-by-value
 * Key idea : Java ALWAYS passes a copy. For a primitive that's a copy of the value;
 *            for an object it's a copy of the reference (so the object itself can change).
 * Run      : java -cp out topic13_methods_and_static.MethodsDemo
 * Try this : add an overload add(double, double) and see which one add(1, 2.5) calls.
 */
public class MethodsDemo {

    // overloading: same name, different parameter lists
    static int add(int a, int b) {
        return a + b;
    }

    static int add(int a, int b, int c) {
        return a + b + c;
    }

    // varargs: zero or more ints, received as an array
    static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }

    static void tryToChange(int number) {
        number = 999;                    // changes only the local copy
    }

    static void changeArray(int[] numbers) {
        numbers[0] = 999;                // same array object -> the caller sees it
    }

    static void reassignArray(int[] numbers) {
        numbers = new int[] {-1};        // the local copy now points elsewhere; caller unaffected
    }

    // recursion: a method that calls itself, with a base case that stops it
    static long factorial(int n) {
        if (n <= 1) {
            return 1;                    // base case
        }
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        System.out.println("add(2, 3)    = " + add(2, 3));
        System.out.println("add(2, 3, 4) = " + add(2, 3, 4));
        System.out.println("sum()        = " + sum());
        System.out.println("sum(1..5)    = " + sum(1, 2, 3, 4, 5));

        int value = 10;
        tryToChange(value);
        System.out.println("primitive after tryToChange: " + value);

        int[] data = {1, 2, 3};
        changeArray(data);
        System.out.println("array after changeArray:     " + Arrays.toString(data));
        reassignArray(data);
        System.out.println("array after reassignArray:   " + Arrays.toString(data));

        System.out.println("factorial(5) = " + factorial(5));
    }
}
