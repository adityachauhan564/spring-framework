package topic07_methods;

import java.util.Arrays;

/*
 * Topic    : Methods - parameters, return values, overloading, varargs, recursion, pass-by-value
 * Key idea : When you call a method, Java ALWAYS sends a COPY (this is called pass-by-value).
 *            - For a primitive (int, double...), the method gets a copy of the VALUE.
 *              Changing it inside the method does not change the caller's variable.
 *              Like giving someone a photocopy of your marksheet - they can scribble on it,
 *              your original is safe.
 *            - For an object (like an array), the method gets a copy of the ADDRESS (reference).
 *              Both copies point to the same object, so the object itself CAN be changed.
 *              Like giving someone a copy of your house key - they can rearrange your furniture.
 * Run      : java -cp out topic07_methods.MethodsDemo
 * Try this : Add an overload add(double, double) and see which one add(1, 2.5) calls.
 */
public class MethodsDemo {

    // overloading: many methods with the SAME name but DIFFERENT parameters.
    // Java picks the right one by looking at what you pass in.
    static int add(int a, int b) {
        return a + b;
    }

    static int add(int a, int b, int c) {
        return a + b + c;
    }

    // varargs (int...): you can pass any number of ints, even zero. Inside, they arrive as an array
    static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }

    static void tryToChange(int number) {
        number = 999;                    // changes only this method's own copy. The caller's value stays 10
    }

    static void changeArray(int[] numbers) {
        numbers[0] = 999;                // both point to the SAME array, so the caller sees this change
    }

    static void reassignArray(int[] numbers) {
        numbers = new int[] {-1};        // our copy now points to a new array. The caller's array is untouched
    }

    // recursion: a method that calls itself.
    // It MUST have a "base case" that stops it, or it calls itself forever and crashes (StackOverflowError).
    // 5! = 5 * 4! = 5 * 4 * 3! ... until we reach 1
    static long factorial(int n) {
        if (n <= 1) {
            return 1;                    // base case: stop here
        }
        return n * factorial(n - 1);     // each call works on a smaller number
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
