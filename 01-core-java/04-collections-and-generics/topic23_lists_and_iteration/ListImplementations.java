package topic23_lists_and_iteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * Topic    : Which List, and how to change a list safely
 * Key idea : ArrayList (an array inside: fast get(i)) vs LinkedList (linked nodes: fast add/remove
 *            at the ends). And three ways to make a list that behave very differently:
 *            new ArrayList<>() changes freely, Arrays.asList(...) is fixed-size, List.of(...) is immutable.
 * Run      : java -cp out topic23_lists_and_iteration.ListImplementations
 * Try this : call add() on the Arrays.asList list and read the exception.
 */
public class ListImplementations {

    public static void main(String[] args) {
        // --- same interface, different insides ---
        List<Integer> arrayList = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> linkedList = new LinkedList<>(List.of(1, 2, 3));
        arrayList.add(0, 0);        // shifts every element one place right: O(n)
        linkedList.add(0, 0);       // relinks the first node: O(1) - but get(i) must walk the chain: O(n)
        System.out.println("ArrayList " + arrayList + ", LinkedList " + linkedList + " - same behaviour");

        // --- three kinds of list ---
        List<String> growable = new ArrayList<>(List.of("a", "b"));
        List<String> fixedSize = Arrays.asList("a", "b");     // backed by an array
        List<String> immutable = List.of("a", "b");

        growable.add("c");
        System.out.println("new ArrayList: add works -> " + growable);

        fixedSize.set(0, "A");                                // set works: the array has a slot
        System.out.print("Arrays.asList: set works -> " + fixedSize);
        try {
            fixedSize.add("c");                               // add can't: an array can't grow
        } catch (UnsupportedOperationException e) {
            System.out.println(", add throws UnsupportedOperationException");
        }

        try {
            immutable.set(0, "A");
        } catch (UnsupportedOperationException e) {
            System.out.println("List.of:       set and add both throw - it can never change");
        }

        // --- removing while looping ---
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        try {
            for (Integer n : numbers) {
                if (n % 2 == 0) {
                    numbers.remove(n);                        // changes the list under the loop
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("remove() inside for-each -> ConcurrentModificationException");
        }

        numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Iterator<Integer> it = numbers.iterator();
        while (it.hasNext()) {
            if (it.next() % 2 == 0) {
                it.remove();                                  // the iterator's own remove is safe
            }
        }
        System.out.println("Iterator.remove() -> " + numbers + " (or simply numbers.removeIf(n -> n % 2 == 0))");
    }
}
