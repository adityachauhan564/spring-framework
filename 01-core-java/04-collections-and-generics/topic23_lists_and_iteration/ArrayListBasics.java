package topic23_lists_and_iteration;

import java.util.ArrayList;
import java.util.List;

/*
 * Topic    : Using the Java library - your first ArrayList (from Head First Java, chapter 6)
 * Key idea : An array has a fixed size. An ArrayList GROWS by itself as you add things.
 *            Like a shopping bag vs an egg tray: the tray holds exactly 12, the bag stretches.
 *            List<Egg> means "a list that can hold only Egg objects".
 * Run      : java -cp out topic23_lists_and_iteration.ArrayListBasics
 * Next     : ArrayListOperations
 */
public class ArrayListBasics {

    static class Egg { }

    public static void main(String[] args) {
        List<Egg> eggs = new ArrayList<>();   // good habit: declare the variable with the interface type List

        Egg egg1 = new Egg();
        Egg egg2 = new Egg();
        eggs.add(egg1);
        eggs.add(egg2);

        System.out.println("How many eggs?     " + eggs.size());
        System.out.println("Contains egg1?    " + eggs.contains(egg1));
        System.out.println("Contains new Egg? " + eggs.contains(new Egg())); // false: a brand-new, different egg object
    }
}
