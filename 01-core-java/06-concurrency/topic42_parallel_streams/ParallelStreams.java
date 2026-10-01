package topic42_parallel_streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

/*
 * Topic    : Parallel streams
 * Key idea : .parallel() splits the work across all your CPU cores.
 *            Like checking 1000 exam papers: one teacher is slow, 8 teachers each taking a pile is fast.
 *            But it only helps when:
 *              - the work is BIG (for 10 papers, dividing them up takes longer than just checking them),
 *              - the work is CPU-heavy, and
 *              - each piece is independent.
 *            And it BREAKS code that changes shared data (like adding to one shared ArrayList).
 * Run      : java -ea -cp out topic42_parallel_streams.ParallelStreams
 *            (-ea switches on the "assert" checks at the end)
 * Try this : Change N to 1_000 and compare the times - parallel can be SLOWER for small work.
 */
public class ParallelStreams {

    private static final long N = 50_000_000L;

    public static void main(String[] args) {
        long start = System.nanoTime();
        long sequentialSum = LongStream.rangeClosed(1, N).sum();                 // one core does everything
        long sequentialMs = (System.nanoTime() - start) / 1_000_000;

        start = System.nanoTime();
        long parallelSum = LongStream.rangeClosed(1, N).parallel().sum();        // all cores share the work
        long parallelMs = (System.nanoTime() - start) / 1_000_000;

        System.out.println("sequential sum = " + sequentialSum + " in " + sequentialMs + " ms");
        System.out.println("parallel sum   = " + parallelSum + " in " + parallelMs + " ms"
                + "   (" + Runtime.getRuntime().availableProcessors() + " cores; timings vary per run)");

        // order: forEach on a parallel stream prints in any order. forEachOrdered keeps the original order
        System.out.print("parallel forEach:        ");
        List.of(1, 2, 3, 4, 5, 6).parallelStream().forEach(n -> System.out.print(n + " "));
        System.out.print("\nparallel forEachOrdered: ");
        List.of(1, 2, 3, 4, 5, 6).parallelStream().forEachOrdered(n -> System.out.print(n + " "));
        System.out.println();

        // WRONG: many threads adding to ONE shared ArrayList - items get lost, or it crashes
        List<Integer> unsafe = new ArrayList<>();
        try {
            LongStream.range(0, 10_000).parallel().forEach(n -> unsafe.add((int) n));
            System.out.println("shared ArrayList size: " + unsafe.size() + "   (expected 10000 - "
                    + (unsafe.size() == 10_000 ? "lucky this run, but it is still a bug)" : "elements were lost)"));
        } catch (RuntimeException e) {             // usually an ArrayIndexOutOfBoundsException
            System.out.println("shared ArrayList threw " + e.getClass().getSimpleName() + " - not thread-safe");
        }

        // RIGHT: let the stream build the result itself - it knows how to do it safely
        List<Long> safe = LongStream.range(0, 10_000).parallel().boxed().toList();
        System.out.println("toList() size:         " + safe.size() + "   (always correct)");

        assert sequentialSum == parallelSum && sequentialSum == N * (N + 1) / 2;
        assert safe.size() == 10_000;
    }
}
