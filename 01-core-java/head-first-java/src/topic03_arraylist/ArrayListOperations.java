package topic03_arraylist;

import java.util.ArrayList;
import java.util.List;

/*
 * Topic    : Everyday ArrayList operations (Head First Java, chapter 6)
 * Key idea : add / get / set / remove / indexOf / contains / isEmpty / loop.
 *            Indexes start at 0, like arrays.
 * Run      : java -cp out topic03_arraylist.ArrayListOperations
 * Try this : remove "Mango" by index instead of by value.
 */
public class ArrayListOperations {

    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();

        // add
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");
        fruits.add(1, "Kiwi");                                  // insert at index 1
        System.out.println("After add:       " + fruits);      // [Apple, Kiwi, Banana, Mango]

        // read
        System.out.println("get(0):          " + fruits.get(0));
        System.out.println("size():          " + fruits.size());
        System.out.println("indexOf(Banana): " + fruits.indexOf("Banana"));
        System.out.println("indexOf(Grape):  " + fruits.indexOf("Grape") + "  (-1 = not found)");
        System.out.println("contains(Mango): " + fruits.contains("Mango"));

        // update
        fruits.set(0, "Green Apple");
        System.out.println("After set(0):    " + fruits);

        // remove
        fruits.remove("Kiwi");                                  // by value
        fruits.remove(0);                                       // by index
        System.out.println("After remove:    " + fruits);      // [Banana, Mango]

        // loop
        for (String fruit : fruits) {
            System.out.println("  - " + fruit);
        }

        // remove while looping: use removeIf, not remove() inside a for-each
        // (that throws ConcurrentModificationException)
        fruits.removeIf(fruit -> fruit.startsWith("B"));
        System.out.println("After removeIf:  " + fruits);      // [Mango]

        fruits.clear();
        System.out.println("isEmpty():       " + fruits.isEmpty());
    }
}
