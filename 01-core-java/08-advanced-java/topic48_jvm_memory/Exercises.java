package topic48_jvm_memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/*
 * Exercises for topic 48. Replace each "TODO" line with your code, then run:
 *   java -cp out topic48_jvm_memory.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // The recursive version overflows the stack for large n: every call is one more frame.
    static long sumRecursive(int n) {
        return n == 0 ? 0 : n + sumRecursive(n - 1);
    }

    // 1. The same sum 1 + 2 + ... + n WITHOUT recursion, so a million works (a loop uses one frame).
    static long sumIterative(int n) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. A cache that can never hold more than maxSize entries: when a new key would exceed it,
    //    the LEAST RECENTLY USED entry is dropped. Hint: new LinkedHashMap<>(16, 0.75f, true)
    //    (access order) and override removeEldestEntry.
    static <K, V> Map<K, V> boundedCache(int maxSize) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    public static void main(String[] args) {
        check(sumRecursive(100) == 5050, "the recursive version works for small n");
        check(sumIterative(1_000_000) == 500_000_500_000L, "exercise 1");

        Map<String, Integer> cache = boundedCache(2);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.get("a");                       // "a" is now the most recently used
        cache.put("c", 3);                    // over the limit: "b" (least recently used) goes
        check(cache.size() == 2 && cache.containsKey("a") && cache.containsKey("c") && !cache.containsKey("b"),
                "exercise 2");

        // 3. The EventBus below leaks: listeners are added but never removed. Implement unsubscribe.
        EventBus bus = new EventBus();
        List<String> received = new ArrayList<>();
        Consumer<String> listener = received::add;
        bus.subscribe(listener);
        bus.publish("one");
        bus.unsubscribe(listener);
        bus.publish("two");
        check(received.equals(List.of("one")) && bus.listenerCount() == 0, "exercise 3");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

class EventBus {
    private final List<Consumer<String>> listeners = new ArrayList<>();

    void subscribe(Consumer<String> listener) {
        listeners.add(listener);
    }

    void unsubscribe(Consumer<String> listener) {
        // TODO exercise 3: without this, every subscriber (and everything it refers to) stays in memory
    }

    void publish(String event) {
        listeners.forEach(listener -> listener.accept(event));
    }

    int listenerCount() {
        return listeners.size();
    }
}
