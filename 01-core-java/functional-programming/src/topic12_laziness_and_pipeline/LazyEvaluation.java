package topic12_laziness_and_pipeline;

import java.util.List;
import java.util.stream.Stream;

/*
 * Topic    : Laziness - how a stream pipeline really runs
 * Key idea : 1. nothing runs until a terminal operation is called;
 *            2. elements flow through the WHOLE pipeline one at a time (not step by step);
 *            3. short-circuit operations (findFirst, limit, anyMatch) stop early.
 *            peek() lets you watch this happen - use it only for debugging.
 * Run      : java -cp out topic12_laziness_and_pipeline.LazyEvaluation
 * Try this : move limit(2) before filter and predict the new trace.
 */
public class LazyEvaluation {

    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "API", "Microservices", "AWS", "Docker");

        // 1. no terminal operation -> nothing is printed
        Stream<String> notRunYet = courses.stream()
                .peek(c -> System.out.println("  never printed: " + c))
                .filter(c -> c.length() > 3);
        System.out.println("1. pipeline built, but no terminal operation - nothing ran");

        // 2. one element at a time, through every step
        System.out.println("2. trace with findFirst:");
        String first = courses.stream()
                .peek(c -> System.out.println("  source:  " + c))
                .filter(c -> c.length() == 3)
                .peek(c -> System.out.println("  passed filter: " + c))
                .map(String::toLowerCase)
                .findFirst()                              // stops at the first match
                .orElse("none");
        System.out.println("  result: " + first + "   (Microservices, AWS, Docker were never read)");

        // 3. limit stops the source early
        System.out.println("3. trace with limit(2):");
        List<String> firstTwoLong = courses.stream()
                .peek(c -> System.out.println("  source:  " + c))
                .filter(c -> c.length() > 3)
                .limit(2)
                .toList();
        System.out.println("  result: " + firstTwoLong);

        // laziness makes infinite streams safe - as long as something stops them
        System.out.println("4. first 5 even squares of an infinite stream: "
                + Stream.iterate(1, n -> n + 1).map(n -> n * n).filter(n -> n % 2 == 0).limit(5).toList());
    }
}
