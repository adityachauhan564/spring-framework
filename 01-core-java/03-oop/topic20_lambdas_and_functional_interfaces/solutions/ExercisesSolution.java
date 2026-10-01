package topic20_lambdas_and_functional_interfaces.solutions;

// Answers for topic20_lambdas_and_functional_interfaces/Exercises.java
public class ExercisesSolution {

    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }

    @FunctionalInterface
    interface TextCheck {
        boolean test(String text);
    }

    static final Calculator POWER = (a, b) -> (int) Math.pow(a, b);

    static final TextCheck IS_LONG = text -> text.length() > 5;

    static int[] combine(int[] a, int[] b, Calculator op) {
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = op.calculate(a[i], b[i]);        // the "what to do" was passed in from outside, as a value
        }
        return result;
    }

    static Calculator addWithBonus(int bonus) {
        return (a, b) -> a + b + bonus;                  // the lambda remembers bonus - allowed because bonus never changes
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
