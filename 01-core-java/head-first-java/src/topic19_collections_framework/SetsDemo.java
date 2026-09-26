package topic19_collections_framework;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
 * Topic    : Set - a collection with NO duplicates
 * Key idea : HashSet     - fastest, no order
 *            LinkedHashSet - keeps insertion order
 *            TreeSet     - always sorted
 * Run      : java -cp out topic19_collections_framework.SetsDemo
 * Next     : QueuesAndDeques, then MapsDemo
 */
public class SetsDemo {

    public static void main(String[] args) {
        List<String> input = List.of("banana", "apple", "cherry", "apple", "banana");

        Set<String> hashSet = new HashSet<>(input);
        Set<String> linkedHashSet = new LinkedHashSet<>(input);
        Set<String> treeSet = new TreeSet<>(input);

        System.out.println("input:         " + input);
        System.out.println("HashSet:       " + hashSet + "   (order not guaranteed)");
        System.out.println("LinkedHashSet: " + linkedHashSet + "   (insertion order)");
        System.out.println("TreeSet:       " + treeSet + "   (sorted)");

        // add() returns false for a duplicate
        System.out.println("add(\"apple\") again -> " + hashSet.add("apple"));

        // set operations
        Set<Integer> a = new TreeSet<>(List.of(1, 2, 3, 4));
        Set<Integer> b = new TreeSet<>(List.of(3, 4, 5));

        Set<Integer> union = new TreeSet<>(a);
        union.addAll(b);
        Set<Integer> intersection = new TreeSet<>(a);
        intersection.retainAll(b);
        Set<Integer> difference = new TreeSet<>(a);
        difference.removeAll(b);

        System.out.println("union " + union + ", intersection " + intersection + ", a - b " + difference);
    }
}
