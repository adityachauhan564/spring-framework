package topic31_terminal_operations_and_reduce.solutions;

import java.util.List;

// Answers for topic31_terminal_operations_and_reduce/Exercises.java
public class ExercisesSolution {

    static String longest(List<String> names) {
        return names.stream()
                .reduce((best, next) -> next.length() > best.length() ? next : best)   // only > (not >=), so on a tie the earlier one stays
                .orElseThrow();                   // reduce with no start value returns an Optional (the list might be empty)
    }

    static int product(List<Integer> numbers) {
        return numbers.stream().reduce(1, (a, b) -> a * b);   // start at 1, because 1 * x == x. Starting at 0 would give 0
    }

    static boolean allLowerCase(List<String> words) {
        return words.stream().allMatch(word -> word.equals(word.toLowerCase()));   // stops at the first word that fails
    }

    static int firstAbove(List<Integer> numbers, int limit) {
        return numbers.stream().filter(n -> n > limit).findFirst().orElse(-1);
    }

    public static void main(String[] args) {
        check(longest(List.of("API", "Docker", "Spring")).equals("Docker"), "exercise 1");
        check(product(List.of(2, 3, 4)) == 24 && product(List.of()) == 1, "exercise 2");
        check(allLowerCase(List.of("java", "streams")) && !allLowerCase(List.of("java", "API")), "exercise 3");
        check(firstAbove(List.of(3, 12, 9, 20), 10) == 12 && firstAbove(List.of(1, 2), 10) == -1, "exercise 4");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
