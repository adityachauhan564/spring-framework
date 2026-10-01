package topic20_lambdas_and_functional_interfaces;

/*
 * Exercises for topic 20.
 * How to use:
 *   - Replace each "null" (and each TODO) with a LAMBDA.
 *   - Then run:  java -cp out topic20_lambdas_and_functional_interfaces.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }

    @FunctionalInterface
    interface TextCheck {
        boolean test(String text);
    }

    // 1. A Calculator for "a to the power b". Hint: (int) Math.pow(a, b)
    static final Calculator POWER = null;               // TODO

    // 2. A TextCheck that is true when the text is longer than 5 characters
    static final TextCheck IS_LONG = null;              // TODO

    // 3. Use the calculator on every pair, one by one: {op(a[0], b[0]), op(a[1], b[1]), ...}
    static int[] combine(int[] a, int[] b, Calculator op) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Return a Calculator that adds 'bonus' to the sum of a and b.
    //    (A lambda that uses a variable from outside itself.)
    static Calculator addWithBonus(int bonus) {
        throw new UnsupportedOperationException("TODO exercise 4");
    }

    public static void main(String[] args) {
        check(POWER != null && POWER.calculate(2, 10) == 1024, "exercise 1");
        check(IS_LONG != null && IS_LONG.test("lambdas") && !IS_LONG.test("java"), "exercise 2");
        int[] sums = combine(new int[] {1, 2}, new int[] {10, 20}, (x, y) -> x + y);
        check(sums.length == 2 && sums[0] == 11 && sums[1] == 22, "exercise 3");
        check(addWithBonus(100).calculate(1, 2) == 103, "exercise 4");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
