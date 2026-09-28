package topic41_executors_and_futures;

import java.util.List;
import java.util.concurrent.ExecutionException;

/*
 * Exercises for topic 41. Replace each "TODO" line with your code, then run:
 *   java -cp out topic41_executors_and_futures.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // Pretend to download a page: slow, returns the page's size.
    static int download(String url) throws InterruptedException {
        Thread.sleep(200);
        return url.length() * 100;
    }

    // 1. "Download" every url IN PARALLEL on a pool of 4 threads and return the total size.
    //    Submit one Callable per url, then add up the Futures' results. Shut the pool down.
    //    Sequentially this takes 200 ms per url; in parallel about 200 ms in total.
    static int totalSize(List<String> urls) throws InterruptedException, ExecutionException {
        throw new UnsupportedOperationException("TODO exercise 1");
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

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
