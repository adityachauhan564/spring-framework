package topic43_completable_future_and_concurrent_collections;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/*
 * Exercises for topic 43. Replace each "TODO" line with your code, then run:
 *   java -cp out topic43_completable_future_and_concurrent_collections.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    static int slowPrice(int value) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return value;
    }

    // 1. Ask two "shops" for a price AT THE SAME TIME (supplyAsync with slowPrice(a) and slowPrice(b))
    //    and complete with the lower one. Don't call join() on the first before starting the second.
    static CompletableFuture<Integer> lowestPrice(int a, int b) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Return a future that fails with the given exception, recovered to the fallback value.
    //    (Hint: CompletableFuture.failedFuture(error), then exceptionally)
    static CompletableFuture<Integer> withFallback(RuntimeException error, int fallback) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Count words with many threads at once (a parallel stream is fine), into a map that
    //    survives concurrent updates. Which Map, and which method makes the update atomic?
    static Map<String, Integer> countWords(List<String> words) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        int lowest = lowestPrice(500, 450).join();
        long ms = (System.nanoTime() - start) / 1_000_000;
        check(lowest == 450, "exercise 1 result");
        check(ms < 550, "exercise 1 took " + ms + " ms - the two calls didn't overlap,");

        check(withFallback(new IllegalStateException("down"), -1).join() == -1, "exercise 2");

        List<String> words = java.util.Collections.nCopies(10_000, "java");
        check(countWords(words).get("java") == 10_000, "exercise 3");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
