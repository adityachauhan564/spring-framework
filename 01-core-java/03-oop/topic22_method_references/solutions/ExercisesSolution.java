package topic22_method_references.solutions;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// Solutions for topic22_method_references/Exercises.java
public class ExercisesSolution {

    static boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) >= 0;
    }

    static final Function<String, Integer> LENGTH = String::length;
    static final Function<String, Integer> TO_NUMBER = Integer::valueOf;
    static final Predicate<Character> VOWEL = ExercisesSolution::isVowel;
    static final BiFunction<String, String, Boolean> SAME_TEXT = String::equalsIgnoreCase;
    static final Supplier<StringBuilder> NEW_BUILDER = StringBuilder::new;

    public static void main(String[] args) {
        check(LENGTH != null && LENGTH.apply("java") == 4, "exercise 1");
        check(TO_NUMBER != null && TO_NUMBER.apply("42") == 42, "exercise 2");
        check(VOWEL != null && VOWEL.test('e') && !VOWEL.test('x'), "exercise 3");
        check(SAME_TEXT != null && SAME_TEXT.apply("Java", "JAVA"), "exercise 4");
        check(NEW_BUILDER != null && NEW_BUILDER.get().append("ok").toString().equals("ok"), "exercise 5");

        Consumer<String> print = System.out::println;
        print.accept("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
