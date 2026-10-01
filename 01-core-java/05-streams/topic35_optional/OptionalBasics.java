package topic35_optional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

/*
 * Topic    : Optional - a box that may or may not have something inside
 * Key idea : A search like findFirst() might find nothing. Instead of giving you null
 *            (which crashes later with NullPointerException), it gives you an Optional<T>.
 *            Like an Amazon delivery box - you must open it and check before using what's inside.
 *            Read it safely with orElse / orElseGet / orElseThrow / ifPresent.
 *            NEVER just call get() - it crashes when the box is empty.
 * Run      : java -cp out topic35_optional.OptionalBasics
 * Next     : OptionalChaining
 */
public class OptionalBasics {

    public static void main(String[] args) {
        List<String> fruits = List.of("apple", "banana", "mango");

        Optional<String> found = findFirst(fruits, fruit -> fruit.startsWith("b"));     // box with "banana"
        Optional<String> missing = findFirst(fruits, fruit -> fruit.startsWith("z"));   // empty box

        System.out.println("found:   " + found + ", isPresent = " + found.isPresent());
        System.out.println("missing: " + missing + ", isEmpty = " + missing.isEmpty());

        // safe ways to read the value
        System.out.println("orElse:        " + missing.orElse("no fruit"));                       // empty? use this default
        System.out.println("orElseGet:     " + missing.orElseGet(() -> "computed only when empty")); // default is made only if needed
        found.ifPresent(fruit -> System.out.println("ifPresent:     " + fruit));                  // do this only if something is inside
        missing.ifPresentOrElse(
                fruit -> System.out.println("never printed"),
                () -> System.out.println("ifPresentOrElse: nothing starts with z"));

        try {
            missing.orElseThrow(() -> new IllegalArgumentException("no fruit starts with z"));    // empty? throw YOUR exception
        } catch (IllegalArgumentException e) {
            System.out.println("orElseThrow:   " + e.getMessage());
        }

        // why not get()? Because it throws an exception when the box is empty
        try {
            missing.get();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("get() on empty: NoSuchElementException - avoid it");
        }

        // making Optionals yourself
        String maybeNull = null;
        System.out.println("Optional.of(\"x\")         = " + Optional.of("x"));               // you are SURE it is not null
        System.out.println("Optional.ofNullable(null) = " + Optional.ofNullable(maybeNull));  // it MIGHT be null
        System.out.println("Optional.empty()          = " + Optional.empty());                // an empty box on purpose
        // Optional.of(null) would throw NullPointerException - use ofNullable when the value may be null

        // wrapping old code that returns null: Map.get gives null for a missing key
        Map<String, Integer> scores = Map.of("Roshan", 9);
        int roshan = Optional.ofNullable(scores.get("Roshan")).orElse(0);
        int rohan = Optional.ofNullable(scores.get("Rohan")).orElse(0);
        System.out.println("scores: Roshan " + roshan + ", Rohan " + rohan + " (missing -> 0, no null check)");
    }

    static Optional<String> findFirst(List<String> items, Predicate<String> condition) {
        return items.stream().filter(condition).findFirst();
    }
}
