package topic26_maps_and_hashing;

import java.util.HashMap;
import java.util.Map;

/*
 * Topic    : HashMap basics
 * Key idea : a Map stores key -> value pairs; each key appears only once.
 *            get() of a missing key returns null; getOrDefault() avoids the null.
 * Run      : java -cp out topic26_maps_and_hashing.HashMapBasics
 * Next     : MapsDemo (choosing a Map, counting), then MyHashMap (how it works inside)
 */
public class HashMapBasics {

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

        System.out.println("size():                " + scores.size());
        System.out.println("get(Rohan):            " + scores.get("Rohan"));          // null, no error
        System.out.println("getOrDefault(Rohan,0): " + scores.getOrDefault("Rohan", 0));
        System.out.println("containsKey(Roshan):   " + scores.containsKey("Roshan"));

        scores.remove(null);
        scores.putIfAbsent("Roshan", 100);     // Roshan exists: nothing changes
        scores.putIfAbsent("Kiran", 7);        // Kiran is new: added
        System.out.println("after remove/putIfAbsent: " + scores);
        // Topic 35 shows Optional: an explicit "maybe a value" instead of a null from get().
    }
}
