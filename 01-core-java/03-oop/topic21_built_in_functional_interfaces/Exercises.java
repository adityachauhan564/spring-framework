package topic21_built_in_functional_interfaces;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * Exercises for topic 21.
 * How to use:
 *   - Replace each "null" and each TODO with your own code.
 *   - Then run:  java -cp out topic21_built_in_functional_interfaces.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    static final Predicate<String> IS_BLANK = String::isBlank;
    static final Predicate<String> IS_SHORT = s -> s.length() < 3;

    // 1. true when the text is blank OR short. Build it from the two predicates above, using or()
    static final Predicate<String> NOT_USEFUL = null;                  // TODO

    // 2. First trim the text, THEN make it upper case. Build it using andThen()
    static final Function<String, String> TRIM = String::trim;
    static final Function<String, String> CLEAN = null;                // TODO: TRIM.andThen(...)

    // 3. The bigger of two ints
    static final BinaryOperator<Integer> MAX = null;                   // TODO

    // 4. Return value if it is not null. Otherwise, ask the supplier for a default value.
    //    (The supplier should be called ONLY when it is really needed -
    //    that's why it is a Supplier and not just a plain value.)
    static <T> T orElseGet(T value, Supplier<T> fallback) {
        throw new UnsupportedOperationException("TODO exercise 4");
    }

    public static void main(String[] args) {
        check(NOT_USEFUL != null && NOT_USEFUL.test("  ") && NOT_USEFUL.test("ab") && !NOT_USEFUL.test("java"),
                "exercise 1");
        check(CLEAN != null && CLEAN.apply("  spring boot ").equals("SPRING BOOT"), "exercise 2");
        check(MAX != null && MAX.apply(3, 9) == 9, "exercise 3");

        // a supplier that counts how many times it was called, so we can check it isn't called for nothing
        int[] calls = {0};
        Supplier<String> counting = () -> {
            calls[0]++;
            return "default";
        };
        check(orElseGet("given", counting).equals("given") && calls[0] == 0, "exercise 4 don't call the supplier");
        check(orElseGet(null, counting).equals("default") && calls[0] == 1, "exercise 4 use the supplier");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
