package topic43_completable_future_and_concurrent_collections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Topic    : Concurrent collections
 * Key idea : HashMap, ArrayList & co. are NOT safe to change from several threads at once.
 *            java.util.concurrent has versions that are: ConcurrentHashMap, CopyOnWriteArrayList,
 *            BlockingQueue. Their compound operations (merge, computeIfAbsent) are atomic too.
 * Run      : java -cp out topic43_completable_future_and_concurrent_collections.ConcurrentCollections
 * Try this : raise the number of tasks and watch the HashMap total drift further.
 */
public class ConcurrentCollections {

    static int countWithEightThreads(Map<String, Integer> counts) {
        try (ExecutorService pool = Executors.newFixedThreadPool(8)) {
            for (int task = 0; task < 8; task++) {
                pool.submit(() -> {
                    for (int i = 0; i < 10_000; i++) {
                        counts.merge("hits", 1, Integer::sum);    // read-modify-write on one key
                    }
                });
            }
        }                                                          // close() waits for all tasks
        return counts.getOrDefault("hits", 0);
    }

    public static void main(String[] args) {
        int expected = 8 * 10_000;
        int plain;
        try {
            plain = countWithEightThreads(new HashMap<>());
        } catch (RuntimeException e) {                             // a corrupted HashMap can even throw
            plain = -1;
        }
        int concurrent = countWithEightThreads(new ConcurrentHashMap<>());

        System.out.println("expected          : " + expected);
        System.out.println("HashMap           : " + plain + "   (usually wrong: updates lost)");
        System.out.println("ConcurrentHashMap : " + concurrent + "   (merge is atomic per key)");
    }
}
