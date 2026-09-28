package topic29_structured_vs_functional;

import java.util.List;

/*
 * Exercises for topic 29. Each method below has a LOOP version in the comment.
 * Write the same thing as ONE stream pipeline, then run:
 *   java -cp out topic29_structured_vs_functional.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. List<Integer> result = new ArrayList<>();
    //    for (int n : numbers) if (n % 2 == 0) result.add(n);
    static List<Integer> evens(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. List<String> result = new ArrayList<>();
    //    for (String c : courses) if (c.startsWith(prefix)) result.add(c);
    static List<String> startingWith(List<String> courses, String prefix) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. long count = 0;
    //    for (String c : courses) if (c.length() >= 6) count++;
    static long countLong(List<String> courses) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 6, 13, 19, 27, 31);
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "Docker");

        check(evens(numbers).equals(List.of(12, 6)), "exercise 1");
        check(startingWith(courses, "Spring").equals(List.of("Spring", "Spring Boot")), "exercise 2");
        check(countLong(courses) == 4, "exercise 3");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
