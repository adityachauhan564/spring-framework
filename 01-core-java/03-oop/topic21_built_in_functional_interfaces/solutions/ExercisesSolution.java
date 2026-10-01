package topic21_built_in_functional_interfaces.solutions;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// Answers for topic21_built_in_functional_interfaces/Exercises.java
public class ExercisesSolution {

    static final Predicate<String> IS_BLANK = String::isBlank;
    static final Predicate<String> IS_SHORT = s -> s.length() < 3;

    static final Predicate<String> NOT_USEFUL = IS_BLANK.or(IS_SHORT);                 // blank OR short

    static final Function<String, String> TRIM = String::trim;
    static final Function<String, String> CLEAN = TRIM.andThen(String::toUpperCase);   // trim first, then upper case

    static final BinaryOperator<Integer> MAX = (a, b) -> a >= b ? a : b;              // Math::max also works

    static <T> T orElseGet(T value, Supplier<T> fallback) {
        return value != null ? value : fallback.get();     // get() runs only when value is null
    }

    public static void main(String[] args) {
        check(NOT_USEFUL != null && NOT_USEFUL.test("  ") && NOT_USEFUL.test("ab") && !NOT_USEFUL.test("java"),
                "exercise 1");
        check(CLEAN != null && CLEAN.apply("  spring boot ").equals("SPRING BOOT"), "exercise 2");
        check(MAX != null && MAX.apply(3, 9) == 9, "exercise 3");

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
