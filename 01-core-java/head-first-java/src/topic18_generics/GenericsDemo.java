package topic18_generics;

import java.util.ArrayList;
import java.util.List;

/*
 * Topic    : Generics
 * Key idea : a type parameter <T> lets one class/method work with any type while the
 *            compiler still checks types - no casts, no ClassCastException at runtime.
 * Run      : java -cp out topic18_generics.GenericsDemo
 * Try this : write a generic method swap(T[] array, int i, int j).
 */
public class GenericsDemo {

    // 1. generic class: a box that holds one value of type T
    static class Box<T> {
        private final T value;

        Box(T value) {
            this.value = value;
        }

        T get() {
            return value;
        }
    }

    // 2. two type parameters (a record is a short data class - see topic24)
    record Pair<K, V>(K first, V second) { }

    // 3. generic method: <T> before the return type
    static <T> T firstOrDefault(List<T> list, T defaultValue) {
        return list.isEmpty() ? defaultValue : list.get(0);
    }

    // 4. bounded type: T must be a Comparable of itself, so compareTo() is allowed
    static <T extends Comparable<T>> T max(List<T> list) {
        T best = list.get(0);
        for (T item : list) {
            if (item.compareTo(best) > 0) {
                best = item;
            }
        }
        return best;
    }

    // 5. wildcard: accepts List<Integer>, List<Double>, ... (any subtype of Number)
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
        String text = textBox.get();              // no cast needed
        System.out.println("Box<String>: " + text + ", Box<Integer>: " + numberBox.get());

        Pair<String, Integer> pair = new Pair<>("age", 25);
        System.out.println("Pair: " + pair.first() + " = " + pair.second());

        System.out.println("firstOrDefault(empty): " + firstOrDefault(new ArrayList<String>(), "none"));
        System.out.println("max of words:  " + max(List.of("pear", "apple", "zebra", "mango")));
        System.out.println("max of ints:   " + max(List.of(3, 9, 4)));
        System.out.println("sum ints:      " + sum(List.of(1, 2, 3)));
        System.out.println("sum doubles:   " + sum(List.of(1.5, 2.5)));

        // Why generics: without them, the mistake shows up only at runtime
        List rawList = new ArrayList();           // raw type (old Java) - avoid
        rawList.add("not a number");
        try {
            Integer broken = (Integer) rawList.get(0);
        } catch (ClassCastException e) {
            System.out.println("Raw list -> ClassCastException at runtime. List<Integer> would not compile.");
        }
    }
}
