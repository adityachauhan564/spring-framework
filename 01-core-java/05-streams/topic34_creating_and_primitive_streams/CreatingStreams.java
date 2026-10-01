package topic34_creating_and_primitive_streams;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * Topic    : Creating streams, and the number streams (IntStream, LongStream, DoubleStream)
 * Key idea : You can start a stream from many places: a list, an array, Stream.of(...),
 *            a range of numbers, iterate() or generate().
 *            IntStream works directly on plain ints - no wrapping into Integer objects (no "boxing"),
 *            so it is faster. It also gives handy extras: sum(), average(), range().
 * Run      : java -cp out topic34_creating_and_primitive_streams.CreatingStreams
 * Try this : Print the first 10 powers of 2 with Stream.iterate.
 */
public class CreatingStreams {

    public static void main(String[] args) {
        // --- different ways to create a stream ---
        System.out.println("List.stream():    " + List.of(1, 2, 3).stream().toList());
        System.out.println("Stream.of:        " + Stream.of("a", "b", "c").toList());
        System.out.println("Arrays.stream:    " + Arrays.stream(new int[] {4, 5, 6}).boxed().toList());
        // iterate: start at 1, and keep making the next value from the previous one (x3 each time)
        System.out.println("iterate (limit):  " + Stream.iterate(1, n -> n * 3).limit(5).toList());     // never ends on its own, so limit() it
        System.out.println("iterate (while):  " + Stream.iterate(1, n -> n < 100, n -> n * 3).toList()); // stops when n < 100 becomes false
        System.out.println("generate:         " + Stream.generate(() -> "hi").limit(3).toList());      // same value again and again

        // --- IntStream ---
        System.out.println("range(1, 5):      " + IntStream.range(1, 5).boxed().toList() + "   (end excluded)");
        System.out.println("rangeClosed(1,5): " + IntStream.rangeClosed(1, 5).boxed().toList() + "   (end included)");
        System.out.println("sum 1..100:       " + IntStream.rangeClosed(1, 100).sum());
        System.out.println("average:          " + IntStream.of(3, 5, 10).average().orElse(0));

        // mapToInt: turn objects into plain ints, so you can use sum() / max() directly
        List<String> courses = List.of("Spring", "API", "Docker");
        int totalLetters = courses.stream().mapToInt(String::length).sum();
        System.out.println("total letters:    " + totalLetters);

        // summaryStatistics: count, sum, min, average and max - all in one go, like a marksheet summary
        IntSummaryStatistics stats = IntStream.of(12, 9, 13, 4, 6).summaryStatistics();
        System.out.println("statistics:       " + stats);

        // boxed(): wrap ints back into Integer objects when you need a List<Integer>
        List<Integer> squares = IntStream.rangeClosed(1, 5).map(n -> n * n).boxed().toList();
        System.out.println("squares:          " + squares);

        // a stream can be used only ONCE - like a train ticket, once it's punched you need a new one
        Stream<String> once = Stream.of("x", "y");
        once.forEach(s -> { });
        try {
            once.forEach(s -> { });
        } catch (IllegalStateException e) {
            System.out.println("reuse:            IllegalStateException - " + e.getMessage());
        }
    }
}
