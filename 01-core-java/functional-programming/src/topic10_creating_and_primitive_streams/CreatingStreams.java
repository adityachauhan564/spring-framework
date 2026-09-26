package topic10_creating_and_primitive_streams;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * Topic    : Creating streams, and the primitive streams (IntStream, LongStream, DoubleStream)
 * Key idea : streams come from collections, arrays, Stream.of, ranges, iterate and generate.
 *            IntStream works on raw ints - no boxing - and adds sum(), average(), range().
 * Run      : java -cp out topic10_creating_and_primitive_streams.CreatingStreams
 * Try this : print the first 10 powers of 2 with Stream.iterate.
 */
public class CreatingStreams {

    public static void main(String[] args) {
        // --- ways to create a stream ---
        System.out.println("List.stream():    " + List.of(1, 2, 3).stream().toList());
        System.out.println("Stream.of:        " + Stream.of("a", "b", "c").toList());
        System.out.println("Arrays.stream:    " + Arrays.stream(new int[] {4, 5, 6}).boxed().toList());
        System.out.println("iterate (limit):  " + Stream.iterate(1, n -> n * 3).limit(5).toList());
        System.out.println("iterate (while):  " + Stream.iterate(1, n -> n < 100, n -> n * 3).toList());
        System.out.println("generate:         " + Stream.generate(() -> "hi").limit(3).toList());

        // --- IntStream ---
        System.out.println("range(1, 5):      " + IntStream.range(1, 5).boxed().toList() + "   (end excluded)");
        System.out.println("rangeClosed(1,5): " + IntStream.rangeClosed(1, 5).boxed().toList() + "   (end included)");
        System.out.println("sum 1..100:       " + IntStream.rangeClosed(1, 100).sum());
        System.out.println("average:          " + IntStream.of(3, 5, 10).average().orElse(0));

        // mapToInt: from objects to ints, to use sum()/max() directly
        List<String> courses = List.of("Spring", "API", "Docker");
        int totalLetters = courses.stream().mapToInt(String::length).sum();
        System.out.println("total letters:    " + totalLetters);

        // summaryStatistics: count, sum, min, average, max in one pass
        IntSummaryStatistics stats = IntStream.of(12, 9, 13, 4, 6).summaryStatistics();
        System.out.println("statistics:       " + stats);

        // boxed(): back from int to Integer when you need a List<Integer>
        List<Integer> squares = IntStream.rangeClosed(1, 5).map(n -> n * n).boxed().toList();
        System.out.println("squares:          " + squares);

        // a stream can be used only ONCE
        Stream<String> once = Stream.of("x", "y");
        once.forEach(s -> { });
        try {
            once.forEach(s -> { });
        } catch (IllegalStateException e) {
            System.out.println("reuse:            IllegalStateException - " + e.getMessage());
        }
    }
}
