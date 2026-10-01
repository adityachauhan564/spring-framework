package topic24_generics;

import java.util.ArrayList;
import java.util.List;

/*
 * Topic    : Generics
 * Key idea : A type parameter like <T> lets you write ONE class or method that works with ANY type,
 *            while the compiler still checks that you use the right type.
 *            Like a tiffin box: the same box can carry rice, roti or dal. But once you label it
 *            "Box<Rice>", nobody can put dal in it by mistake.
 *            Result: no casting, and no ClassCastException surprises while the program runs.
 * Run      : java -cp out topic24_generics.GenericsDemo
 * Try this : Write a generic method swap(T[] array, int i, int j).
 */
public class GenericsDemo {

    // 1. a generic class: a box that holds one value of type T (T is decided when you create the box)
    static class Box<T> {
        private final T value;

        Box(T value) {
            this.value = value;
        }

        T get() {
            return value;
        }
    }

    // 2. two type parameters, K and V (a record is a short data class - see topic 17)
    record Pair<K, V>(K first, V second) { }

    // 3. a generic method: the <T> is written just before the return type
    static <T> T firstOrDefault(List<T> list, T defaultValue) {
        return list.isEmpty() ? defaultValue : list.get(0);
    }

    // 4. bounded type: "T can be any type, AS LONG AS it can be compared with itself".
    //    That's why we are allowed to call compareTo() here
    static <T extends Comparable<T>> T max(List<T> list) {
        T best = list.get(0);
        for (T item : list) {
            if (item.compareTo(best) > 0) {           // compareTo > 0 means "item is bigger than best"
                best = item;
            }
        }
        return best;
    }

    // 5. wildcard <? extends Number>: accepts List<Integer>, List<Double>, ... any kind of Number
    static double sum(List<? extends Number> numbers) {
        double total = 0;
        for (Number n : numbers) {
            total += n.doubleValue();
        }
        return total;
    }

    public static void main(String[] args) {
        Box<String> textBox = new Box<>("hello");
        Box<Integer> numberBox = new Box<>(42);
        String text = textBox.get();              // no cast needed - the compiler already knows it's a String
        System.out.println("Box<String>: " + text + ", Box<Integer>: " + numberBox.get());

        Pair<String, Integer> pair = new Pair<>("age", 25);
        System.out.println("Pair: " + pair.first() + " = " + pair.second());

        System.out.println("firstOrDefault(empty): " + firstOrDefault(new ArrayList<String>(), "none"));
        System.out.println("max of words:  " + max(List.of("pear", "apple", "zebra", "mango")));
        System.out.println("max of ints:   " + max(List.of(3, 9, 4)));
        System.out.println("sum ints:      " + sum(List.of(1, 2, 3)));
        System.out.println("sum doubles:   " + sum(List.of(1.5, 2.5)));

        // Why generics? Without them, a mistake shows up only while the program runs
        List rawList = new ArrayList();           // "raw type" - the old Java way without <...>. Avoid it
        rawList.add("not a number");
        try {
            Integer broken = (Integer) rawList.get(0);
        } catch (ClassCastException e) {
            System.out.println("Raw list -> ClassCastException at runtime. List<Integer> would not compile.");
        }
    }
}
