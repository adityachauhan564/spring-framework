package topic48_jvm_memory.solutions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

// Solutions for topic48_jvm_memory/Exercises.java
public class ExercisesSolution {

    static long sumRecursive(int n) {
        return n == 0 ? 0 : n + sumRecursive(n - 1);
    }

    static long sumIterative(int n) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {        // one stack frame, however large n is
            sum += i;
        }
        return sum;
    }

    static <K, V> Map<K, V> boundedCache(int maxSize) {
        return new LinkedHashMap<>(16, 0.75f, true) {          // true = order by ACCESS, not insertion
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxSize;                        // drop the least recently used
            }
        };
    }

    public static void main(String[] args) {
        check(sumRecursive(100) == 5050, "the recursive version works for small n");
        check(sumIterative(1_000_000) == 500_000_500_000L, "exercise 1");

        Map<String, Integer> cache = boundedCache(2);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.get("a");
        cache.put("c", 3);
        check(cache.size() == 2 && cache.containsKey("a") && cache.containsKey("c") && !cache.containsKey("b"),
                "exercise 2");

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
        listeners.remove(listener);           // unreachable again, so the GC can free it
    }

    void publish(String event) {
        listeners.forEach(listener -> listener.accept(event));
    }

    int listenerCount() {
        return listeners.size();
    }
}
