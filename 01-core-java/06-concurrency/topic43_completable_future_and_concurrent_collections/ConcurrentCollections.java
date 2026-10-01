package topic43_completable_future_and_concurrent_collections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Topic    : Concurrent collections
 * Key idea : HashMap, ArrayList and friends are NOT safe when many threads change them at the same time.
 *            java.util.concurrent has safe versions: ConcurrentHashMap, CopyOnWriteArrayList,
 *            BlockingQueue. Even their combined steps (like merge, computeIfAbsent) happen as one safe step.
 *            Like a vote-counting hall: a plain HashMap is 8 people writing on one tally sheet at once
 *            (counts get messed up). ConcurrentHashMap gives each update its turn, so no vote is lost.
 * Run      : java -cp out topic43_completable_future_and_concurrent_collections.ConcurrentCollections
 * Try this : Increase the number of tasks and watch the HashMap total go even more wrong.
 */
public class ConcurrentCollections {

    // 8 threads, each adds 10,000 hits to the same map key
    static int countWithEightThreads(Map<String, Integer> counts) {
        try (ExecutorService pool = Executors.newFixedThreadPool(8)) {
            for (int task = 0; task < 8; task++) {
                pool.submit(() -> {
                    for (int i = 0; i < 10_000; i++) {
                        counts.merge("hits", 1, Integer::sum);    // read + change + write on one key
                    }
                });
            }
        }                                                          // close() waits until all the jobs finish
        return counts.getOrDefault("hits", 0);
    }

    public static void main(String[] args) {
        int expected = 8 * 10_000;
        int plain;
        try {
            plain = countWithEightThreads(new HashMap<>());
        } catch (RuntimeException e) {                             // a HashMap messed up by many threads can even throw
            plain = -1;
        }
        int concurrent = countWithEightThreads(new ConcurrentHashMap<>());

        System.out.println("expected          : " + expected);
        System.out.println("HashMap           : " + plain + "   (usually wrong: updates lost)");
        System.out.println("ConcurrentHashMap : " + concurrent + "   (merge is atomic per key)");
    }
}
