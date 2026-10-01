package topic26_maps_and_hashing;

import java.util.HashMap;
import java.util.Map;

/*
 * Topic    : HashMap basics
 * Key idea : A Map stores pairs: key -> value. Each key can appear only ONCE.
 *            Like your phone contacts: name (key) -> phone number (value).
 *            Save the same name again and the old number is replaced.
 *            - get() of a key that isn't there returns null (no error).
 *            - getOrDefault() gives you a fallback value instead of that null.
 * Run      : java -cp out topic26_maps_and_hashing.HashMapBasics
 * Next     : MapsDemo (choosing a Map, counting), then MyHashMap (how it works inside)
 */
public class HashMapBasics {

    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Aditya", 1);
        scores.put("Roshan", 9);
        scores.put("Aditya", 5);        // same key again: the old value 1 is replaced by 5
        scores.put(null, null);         // a HashMap allows one null key, and null values

        // loop through every pair. Careful: a HashMap does NOT promise any order
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("size():                " + scores.size());
        System.out.println("get(Rohan):            " + scores.get("Rohan"));          // not there: null, but no error
        System.out.println("getOrDefault(Rohan,0): " + scores.getOrDefault("Rohan", 0));
        System.out.println("containsKey(Roshan):   " + scores.containsKey("Roshan"));

        scores.remove(null);
        scores.putIfAbsent("Roshan", 100);     // Roshan is already there: nothing changes
        scores.putIfAbsent("Kiran", 7);        // Kiran is new: gets added
        System.out.println("after remove/putIfAbsent: " + scores);
        // Topic 35 shows Optional: a clear "maybe there is a value" box, instead of a null from get().
    }
}
