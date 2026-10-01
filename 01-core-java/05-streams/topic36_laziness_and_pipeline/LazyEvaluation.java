package topic36_laziness_and_pipeline;

import java.util.List;
import java.util.stream.Stream;

/*
 * Topic    : Laziness - how a stream pipeline REALLY runs
 * Key idea : Streams are "lazy" - they do no work until they have to:
 *            1. Nothing runs until you call a terminal operation (like toList or findFirst).
 *               Building the pipeline is just writing the recipe, not cooking.
 *            2. Items go through the WHOLE pipeline ONE AT A TIME - not all items through
 *               step 1, then all through step 2. Like idlis: each one goes from mould to plate
 *               before the next one is made.
 *            3. Short-circuit operations (findFirst, limit, anyMatch) stop as soon as they have the answer.
 *            peek() lets you watch this happen. Use peek() only for debugging.
 * Run      : java -cp out topic36_laziness_and_pipeline.LazyEvaluation
 * Try this : Move limit(2) before filter and guess the new trace before running.
 */
public class LazyEvaluation {

    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "API", "Microservices", "AWS", "Docker");

        // 1. no terminal operation -> nothing runs, so nothing is printed
        Stream<String> notRunYet = courses.stream()
                .peek(c -> System.out.println("  never printed: " + c))
                .filter(c -> c.length() > 3);
        System.out.println("1. pipeline built, but no terminal operation - nothing ran");

        // 2. one item at a time, through every step
        System.out.println("2. trace with findFirst:");
        String first = courses.stream()
                .peek(c -> System.out.println("  source:  " + c))
                .filter(c -> c.length() == 3)
                .peek(c -> System.out.println("  passed filter: " + c))
                .map(String::toLowerCase)
                .findFirst()                              // stops at the very first match
                .orElse("none");
        System.out.println("  result: " + first + "   (Microservices, AWS, Docker were never read)");

        // 3. limit(2) stops pulling from the source once it has 2 items
        System.out.println("3. trace with limit(2):");
        List<String> firstTwoLong = courses.stream()
                .peek(c -> System.out.println("  source:  " + c))
                .filter(c -> c.length() > 3)
                .limit(2)
                .toList();
        System.out.println("  result: " + firstTwoLong);

        // because of laziness, even an endless stream is safe - as long as something (like limit) stops it
        System.out.println("4. first 5 even squares of an infinite stream: "
                + Stream.iterate(1, n -> n + 1).map(n -> n * n).filter(n -> n % 2 == 0).limit(5).toList());
    }
}
