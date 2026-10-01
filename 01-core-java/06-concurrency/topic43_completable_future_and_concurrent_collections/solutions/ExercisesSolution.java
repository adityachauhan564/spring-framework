package topic43_completable_future_and_concurrent_collections.solutions;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

// Answers for topic43_completable_future_and_concurrent_collections/Exercises.java
public class ExercisesSolution {

    // helper: pretends to ask a shop for a price - takes 300 ms, then returns the value
    static int slowPrice(int value) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return value;
    }

    static CompletableFuture<Integer> lowestPrice(int a, int b) {
        CompletableFuture<Integer> first = CompletableFuture.supplyAsync(() -> slowPrice(a));    // both start right now,
        CompletableFuture<Integer> second = CompletableFuture.supplyAsync(() -> slowPrice(b));   // side by side
        return first.thenCombine(second, Math::min);             // runs once BOTH are ready - nobody sits and waits
    }

    static CompletableFuture<Integer> withFallback(RuntimeException error, int fallback) {
        return CompletableFuture.<Integer>failedFuture(error).exceptionally(e -> fallback);   // failed? use the backup value
    }

    static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> counts = new ConcurrentHashMap<>();
        words.parallelStream().forEach(word -> counts.merge(word, 1, Integer::sum));   // merge is one safe step for each key
        return counts;
        // even simpler: words.parallelStream().collect(Collectors.groupingByConcurrent(w -> w, Collectors.summingInt(w -> 1)))
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

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
