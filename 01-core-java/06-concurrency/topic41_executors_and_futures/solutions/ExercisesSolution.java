package topic41_executors_and_futures.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Answers for topic41_executors_and_futures/Exercises.java
public class ExercisesSolution {

    // Pretends to download a web page: slow (200 ms), returns the page's size.
    static int download(String url) throws InterruptedException {
        Thread.sleep(200);
        return url.length() * 100;
    }

    static int totalSize(List<String> urls) throws InterruptedException, ExecutionException {
        try (ExecutorService pool = Executors.newFixedThreadPool(4)) {   // close() at the end shuts the pool down
            List<Future<Integer>> sizes = new ArrayList<>();
            for (String url : urls) {
                sizes.add(pool.submit(() -> download(url)));   // a Callable: it returns a value, and is allowed to throw
            }
            int total = 0;
            for (Future<Integer> size : sizes) {               // submit ALL jobs first, only then start waiting
                total += size.get();
            }
            return total;
        }
    }

    public static void main(String[] args) throws Exception {
        List<String> urls = List.of("a.com", "bb.com", "ccc.com", "dddd.com");
        long start = System.nanoTime();
        int total = totalSize(urls);
        long ms = (System.nanoTime() - start) / 1_000_000;
        check(total == (5 + 6 + 7 + 8) * 100, "exercise 1 total");
        check(ms < 600, "exercise 1 ran in " + ms + " ms - the downloads didn't run in parallel,");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
