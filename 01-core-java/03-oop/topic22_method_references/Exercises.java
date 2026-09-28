package topic22_method_references;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * Exercises for topic 22. Replace each "null" with a METHOD REFERENCE doing the same as the
 * lambda in the comment, then run:
 *   java -cp out topic22_method_references.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    static boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) >= 0;
    }

    // 1. s -> s.length()                               (instance method of the argument)
    static final Function<String, Integer> LENGTH = null;

    // 2. s -> Integer.valueOf(s)                       (static method)
    static final Function<String, Integer> TO_NUMBER = null;

    // 3. c -> Exercises.isVowel(c)                     (your own static method)
    static final Predicate<Character> VOWEL = null;

    // 4. (s, other) -> s.equalsIgnoreCase(other)       (instance method of the first argument)
    static final BiFunction<String, String, Boolean> SAME_TEXT = null;

    // 5. () -> new StringBuilder()                     (constructor)
    static final Supplier<StringBuilder> NEW_BUILDER = null;

    public static void main(String[] args) {
        check(LENGTH != null && LENGTH.apply("java") == 4, "exercise 1");
        check(TO_NUMBER != null && TO_NUMBER.apply("42") == 42, "exercise 2");
        check(VOWEL != null && VOWEL.test('e') && !VOWEL.test('x'), "exercise 3");
        check(SAME_TEXT != null && SAME_TEXT.apply("Java", "JAVA"), "exercise 4");
        check(NEW_BUILDER != null && NEW_BUILDER.get().append("ok").toString().equals("ok"), "exercise 5");

        Consumer<String> print = System.out::println;      // kind 2: a particular object's method
        print.accept("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
