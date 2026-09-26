package topic07_hashmap_and_optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/*
 * Topic    : HashMap and Optional
 * Key idea : a Map stores key -> value pairs; a key appears only once.
 *            Optional wraps "maybe a value" so you don't get a NullPointerException.
 * Run      : java -cp out topic07_hashmap_and_optional.HashMapAndOptional
 * Next     : MyHashMap (how a HashMap works inside)
 */
public class HashMapAndOptional {

    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Aditya", 1);
        scores.put("Roshan", 9);
        scores.put("Aditya", 5);        // same key: the old value is replaced
        scores.put(null, null);         // HashMap allows one null key and null values

        // iterate: the order is NOT guaranteed in a HashMap
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("get(Rohan):            " + scores.get("Rohan"));          // null, no error
        System.out.println("getOrDefault(Rohan,0): " + scores.getOrDefault("Rohan", 0));
        System.out.println("containsKey(Roshan):   " + scores.containsKey("Roshan"));

        // Optional: make "value might be missing" explicit
        Optional<Integer> rohan = Optional.ofNullable(scores.get("Rohan"));
        rohan.ifPresentOrElse(
                score -> System.out.println("Rohan's score is " + score),
                () -> System.out.println("Rohan is not in the map"));

        int roshanScore = Optional.ofNullable(scores.get("Roshan")).orElse(0);
        System.out.println("Roshan's score is " + roshanScore);
        // Avoid optional.get() without checking - it throws if empty.
    }
}
