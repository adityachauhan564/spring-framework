package topic23_lists_and_iteration;

import java.util.ArrayList;
import java.util.List;

/*
 * Topic    : Everyday ArrayList operations (from Head First Java, chapter 6)
 * Key idea : The methods you will use daily: add, get, set, remove, indexOf, contains, isEmpty,
 *            and looping. Positions (indexes) start at 0, same as arrays.
 *            Think of it as your grocery list on the phone - add, change, tick off, clear.
 * Run      : java -cp out topic23_lists_and_iteration.ArrayListOperations
 * Try this : Remove "Mango" using its index instead of its value.
 */
public class ArrayListOperations {

    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();

        // add - puts it at the end
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");
        fruits.add(1, "Kiwi");                                  // add at position 1 - the others move one step right
        System.out.println("After add:       " + fruits);      // [Apple, Kiwi, Banana, Mango]

        // read
        System.out.println("get(0):          " + fruits.get(0));
        System.out.println("size():          " + fruits.size());
        System.out.println("indexOf(Banana): " + fruits.indexOf("Banana"));
        System.out.println("indexOf(Grape):  " + fruits.indexOf("Grape") + "  (-1 = not found)");
        System.out.println("contains(Mango): " + fruits.contains("Mango"));

        // update - replace what is at a position
        fruits.set(0, "Green Apple");
        System.out.println("After set(0):    " + fruits);

        // remove
        fruits.remove("Kiwi");                                  // remove by value
        fruits.remove(0);                                       // remove by position
        System.out.println("After remove:    " + fruits);      // [Banana, Mango]

        // loop through every item
        for (String fruit : fruits) {
            System.out.println("  - " + fruit);
        }

        // Want to remove items while looping? Use removeIf.
        // Calling remove() inside a for-each loop crashes with ConcurrentModificationException
        fruits.removeIf(fruit -> fruit.startsWith("B"));        // "remove every fruit that starts with B"
        System.out.println("After removeIf:  " + fruits);      // [Mango]

        fruits.clear();                                         // empty the whole list
        System.out.println("isEmpty():       " + fruits.isEmpty());
    }
}
