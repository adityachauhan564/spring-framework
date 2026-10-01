package topic23_lists_and_iteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * Topic    : Which List to pick, and how to change a list safely
 * Key idea : 1. ArrayList vs LinkedList - same methods, different insides:
 *               ArrayList  = an array inside. get(i) is fast, like jumping to seat 25 in a cinema row.
 *               LinkedList = a chain of nodes, each holding the next one's hand, like a train.
 *                            Adding/removing at the ends is fast, but reaching item i means
 *                            walking from the start, coach by coach.
 *            2. Three ways to make a list, and they behave very differently:
 *               new ArrayList<>()  - change it any way you like
 *               Arrays.asList(...) - fixed size: you can replace items, but not add/remove
 *               List.of(...)       - completely locked (immutable): no changes at all
 * Run      : java -cp out topic23_lists_and_iteration.ListImplementations
 * Try this : Call add() on the Arrays.asList list and read the exception.
 */
public class ListImplementations {

    public static void main(String[] args) {
        // --- same interface, different insides ---
        List<Integer> arrayList = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> linkedList = new LinkedList<>(List.of(1, 2, 3));
        arrayList.add(0, 0);        // every item has to shift one place right - slow for big lists: O(n)
        linkedList.add(0, 0);       // just attach a new first coach - fast: O(1). But get(i) must walk the chain: O(n)
        System.out.println("ArrayList " + arrayList + ", LinkedList " + linkedList + " - same behaviour");

        // --- three kinds of list ---
        List<String> growable = new ArrayList<>(List.of("a", "b"));
        List<String> fixedSize = Arrays.asList("a", "b");     // sits on top of a plain array
        List<String> immutable = List.of("a", "b");

        growable.add("c");
        System.out.println("new ArrayList: add works -> " + growable);

        fixedSize.set(0, "A");                                // set works: the box already exists, we just replace what's in it
        System.out.print("Arrays.asList: set works -> " + fixedSize);
        try {
            fixedSize.add("c");                               // add fails: an array can't grow
        } catch (UnsupportedOperationException e) {
            System.out.println(", add throws UnsupportedOperationException");
        }

        try {
            immutable.set(0, "A");
        } catch (UnsupportedOperationException e) {
            System.out.println("List.of:       set and add both throw - it can never change");
        }

        // --- removing items while looping ---
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        try {
            for (Integer n : numbers) {
                if (n % 2 == 0) {
                    numbers.remove(n);                        // WRONG: changing the list while the loop is walking on it
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("remove() inside for-each -> ConcurrentModificationException");
        }

        numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Iterator<Integer> it = numbers.iterator();            // an iterator walks through the list one item at a time
        while (it.hasNext()) {
            if (it.next() % 2 == 0) {
                it.remove();                                  // RIGHT: the iterator's own remove() is safe
            }
        }
        System.out.println("Iterator.remove() -> " + numbers + " (or simply numbers.removeIf(n -> n % 2 == 0))");
    }
}
