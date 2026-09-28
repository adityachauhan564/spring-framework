package topic35_optional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

/*
 * Topic    : Optional - a box that may or may not hold a value
 * Key idea : a search like findFirst() may find nothing, so it returns Optional<T> instead of
 *            null. Read it safely with orElse / orElseGet / orElseThrow / ifPresent -
 *            never with a bare get().
 * Run      : java -cp out topic35_optional.OptionalBasics
 * Next     : OptionalChaining
 */
public class OptionalBasics {

    public static void main(String[] args) {
        List<String> fruits = List.of("apple", "banana", "mango");

        Optional<String> found = findFirst(fruits, fruit -> fruit.startsWith("b"));
        Optional<String> missing = findFirst(fruits, fruit -> fruit.startsWith("z"));

        System.out.println("found:   " + found + ", isPresent = " + found.isPresent());
        System.out.println("missing: " + missing + ", isEmpty = " + missing.isEmpty());

        // safe ways to read the value
        System.out.println("orElse:        " + missing.orElse("no fruit"));
        System.out.println("orElseGet:     " + missing.orElseGet(() -> "computed only when empty"));
        found.ifPresent(fruit -> System.out.println("ifPresent:     " + fruit));
        missing.ifPresentOrElse(
                fruit -> System.out.println("never printed"),
                () -> System.out.println("ifPresentOrElse: nothing starts with z"));

        try {
            missing.orElseThrow(() -> new IllegalArgumentException("no fruit starts with z"));
        } catch (IllegalArgumentException e) {
            System.out.println("orElseThrow:   " + e.getMessage());
        }

        // why not get()? It throws when the Optional is empty.
        try {
            missing.get();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("get() on empty: NoSuchElementException - avoid it");
        }

        // creating Optionals yourself
        String maybeNull = null;
        System.out.println("Optional.of(\"x\")         = " + Optional.of("x"));
        System.out.println("Optional.ofNullable(null) = " + Optional.ofNullable(maybeNull));
        System.out.println("Optional.empty()          = " + Optional.empty());
        // Optional.of(null) would throw NullPointerException - use ofNullable when it may be null

        // wrapping an old API that returns null: Map.get gives null for a missing key
        Map<String, Integer> scores = Map.of("Roshan", 9);
        int roshan = Optional.ofNullable(scores.get("Roshan")).orElse(0);
        int rohan = Optional.ofNullable(scores.get("Rohan")).orElse(0);
        System.out.println("scores: Roshan " + roshan + ", Rohan " + rohan + " (missing -> 0, no null check)");
    }

    static Optional<String> findFirst(List<String> items, Predicate<String> condition) {
        return items.stream().filter(condition).findFirst();
    }
}
