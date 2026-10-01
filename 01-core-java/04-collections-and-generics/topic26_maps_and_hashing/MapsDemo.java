package topic26_maps_and_hashing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * Topic    : Choosing a Map, counting with merge(), and the Iterator
 * Key idea : Same three choices as with Sets:
 *              HashMap       - no order (fastest)
 *              LinkedHashMap - remembers the order keys were added
 *              TreeMap       - keys always sorted
 *            To remove pairs while looping, use an Iterator.
 * Run      : java -cp out topic26_maps_and_hashing.MapsDemo
 * Try this : Count the characters of "mississippi" instead of words.
 */
public class MapsDemo {

    public static void main(String[] args) {
        List<String> words = List.of("java", "is", "fun", "java", "is", "java");

        // counting words, like counting votes in a class monitor election.
        // merge(word, 1, Integer::sum) means: new word -> start at 1, already there -> add 1
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.merge(word, 1, Integer::sum);
        }
        System.out.println("HashMap:       " + counts);
        System.out.println("TreeMap:       " + new TreeMap<>(counts) + "   (sorted by key)");

        Map<String, Integer> ordered = new LinkedHashMap<>();
        for (String word : words) {
            ordered.merge(word, 1, Integer::sum);
        }
        System.out.println("LinkedHashMap: " + ordered + "   (first-seen order)");

        // you can loop over only the keys, only the values, or both together
        System.out.println("keys " + ordered.keySet() + ", values " + ordered.values());
        ordered.forEach((word, count) -> System.out.println("  " + word + " x" + count));

        // TreeMap extras: because keys are sorted, it can answer "nearest key" questions
        TreeMap<Integer, String> grades = new TreeMap<>(Map.of(90, "A", 80, "B", 70, "C"));
        System.out.println("grade for 85: " + grades.floorEntry(85).getValue() + "   (floorEntry = largest key <= 85)");

        // removing while looping: Iterator.remove() is safe. map.remove() inside a for-each is NOT
        Iterator<Map.Entry<String, Integer>> it = ordered.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue() == 1) {
                it.remove();
            }
        }
        System.out.println("after removing count 1: " + ordered);
    }
}
