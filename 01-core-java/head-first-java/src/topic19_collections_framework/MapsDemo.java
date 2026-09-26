package topic19_collections_framework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * Topic    : Choosing a Map, counting with merge(), and the Iterator
 * Key idea : HashMap (no order) / LinkedHashMap (insertion order) / TreeMap (sorted keys) -
 *            the same idea as the three Sets. Use an Iterator to remove while looping.
 * Run      : java -cp out topic19_collections_framework.MapsDemo
 * Try this : count the characters of "mississippi" instead of words.
 */
public class MapsDemo {

    public static void main(String[] args) {
        List<String> words = List.of("java", "is", "fun", "java", "is", "java");

        // word count: merge() adds 1, or starts at 1 when the key is new
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

        // loop over keys, values, or both
        System.out.println("keys " + ordered.keySet() + ", values " + ordered.values());
        ordered.forEach((word, count) -> System.out.println("  " + word + " x" + count));

        // TreeMap extras
        TreeMap<Integer, String> grades = new TreeMap<>(Map.of(90, "A", 80, "B", 70, "C"));
        System.out.println("grade for 85: " + grades.floorEntry(85).getValue() + "   (floorEntry = largest key <= 85)");

        // remove while iterating: Iterator.remove() is safe, map.remove() inside for-each is not
        Iterator<Map.Entry<String, Integer>> it = ordered.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue() == 1) {
                it.remove();
            }
        }
        System.out.println("after removing count 1: " + ordered);
    }
}
