package topic06_intermediate_operations;

import java.util.Comparator;
import java.util.List;

/*
 * Topic    : Intermediate operations - each one returns a NEW stream
 * Key idea : map (transform), filter (keep), distinct, sorted, limit, skip, takeWhile, dropWhile.
 *            They chain together; the original list is never changed.
 * Run      : java -cp out topic06_intermediate_operations.IntermediateOperations
 * Try this : print the squares of the distinct odd numbers, largest first.
 */
public class IntermediateOperations {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "Docker");

        System.out.println("map (square):      " + numbers.stream().map(n -> n * n).toList());
        System.out.println("map (length):      " + courses.stream().map(String::length).toList());
        System.out.println("distinct:          " + numbers.stream().distinct().toList());
        System.out.println("sorted:            " + numbers.stream().sorted().toList());
        System.out.println("sorted reversed:   " + numbers.stream().sorted(Comparator.reverseOrder()).toList());
        System.out.println("sorted by length:  " + courses.stream().sorted(Comparator.comparing(String::length)).toList());
        System.out.println("limit(3):          " + numbers.stream().limit(3).toList());
        System.out.println("skip(3):           " + numbers.stream().skip(3).toList());
        System.out.println("takeWhile(> 5):    " + numbers.stream().takeWhile(n -> n > 5).toList() + "   (stops at the first failure)");
        System.out.println("dropWhile(> 5):    " + numbers.stream().dropWhile(n -> n > 5).toList());

        // chaining: distinct even numbers, squared, sorted
        List<Integer> result = numbers.stream()
                .filter(n -> n % 2 == 0)
                .distinct()
                .map(n -> n * n)
                .sorted()
                .toList();
        System.out.println("chain:             " + result);
        System.out.println("original list:     " + numbers + "   (unchanged)");
    }
}
