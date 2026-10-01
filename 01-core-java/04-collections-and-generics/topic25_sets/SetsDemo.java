package topic25_sets;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
 * Topic    : Set - a collection that NEVER keeps duplicates
 * Key idea : Like a guest list for a wedding - each name is written only once,
 *            even if two relatives tell you the same name.
 *            Three kinds, pick by the order you need:
 *              HashSet       - the fastest, but the order is random
 *              LinkedHashSet - remembers the order you added things in
 *              TreeSet       - always kept sorted (A to Z, small to big)
 * Run      : java -cp out topic25_sets.SetsDemo
 * Next     : QueuesAndDeques, then MapsDemo
 */
public class SetsDemo {

    public static void main(String[] args) {
        List<String> input = List.of("banana", "apple", "cherry", "apple", "banana");   // has duplicates

        Set<String> hashSet = new HashSet<>(input);
        Set<String> linkedHashSet = new LinkedHashSet<>(input);
        Set<String> treeSet = new TreeSet<>(input);

        System.out.println("input:         " + input);
        System.out.println("HashSet:       " + hashSet + "   (order not guaranteed)");
        System.out.println("LinkedHashSet: " + linkedHashSet + "   (insertion order)");
        System.out.println("TreeSet:       " + treeSet + "   (sorted)");

        // add() returns false if the item is already there - a handy way to spot duplicates
        System.out.println("add(\"apple\") again -> " + hashSet.add("apple"));

        // set maths, like the Venn diagrams from school
        Set<Integer> a = new TreeSet<>(List.of(1, 2, 3, 4));
        Set<Integer> b = new TreeSet<>(List.of(3, 4, 5));

        Set<Integer> union = new TreeSet<>(a);          // union: everything in a OR b
        union.addAll(b);
        Set<Integer> intersection = new TreeSet<>(a);   // intersection: only what is in BOTH a and b
        intersection.retainAll(b);
        Set<Integer> difference = new TreeSet<>(a);     // difference: in a, but NOT in b
        difference.removeAll(b);

        System.out.println("union " + union + ", intersection " + intersection + ", a - b " + difference);
    }
}
