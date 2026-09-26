package topic03_arraylist;

import java.util.ArrayList;
import java.util.List;

/*
 * Topic    : Using the Java library - first ArrayList (Head First Java, chapter 6)
 * Key idea : an ArrayList grows by itself; <Egg> means "a list of Egg objects".
 * Run      : java -cp out topic03_arraylist.ArrayListBasics
 * Next     : ArrayListOperations
 */
public class ArrayListBasics {

    static class Egg { }

    public static void main(String[] args) {
        List<Egg> eggs = new ArrayList<>();   // declare with the interface type List

        Egg egg1 = new Egg();
        Egg egg2 = new Egg();
        eggs.add(egg1);
        eggs.add(egg2);

        System.out.println("How many eggs?     " + eggs.size());
        System.out.println("Contains egg1?    " + eggs.contains(egg1));
        System.out.println("Contains new Egg? " + eggs.contains(new Egg())); // false: a different object
    }
}
