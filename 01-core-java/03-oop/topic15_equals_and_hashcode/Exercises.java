package topic15_equals_and_hashcode;

import java.util.HashSet;
import java.util.Set;

/*
 * Exercises for topic 15. Complete the Book class below this one, then run:
 *   java -cp out topic15_equals_and_hashcode.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    public static void main(String[] args) {
        Book a = new Book("978-0596009205", "Head First Java", 2005);
        Book sameIsbn = new Book("978-0596009205", "Head First Java (2nd printing)", 2006);
        Book other = new Book("978-1491910740", "Head First Java 3e", 2022);

        // 1. Two books are equal when their ISBN is equal - title and year don't matter
        check(a.equals(sameIsbn) && !a.equals(other), "exercise 1 equals");
        check(!a.equals(null) && !a.equals("978-0596009205"), "exercise 1 equals with null / another type");

        // 2. ...so equal books must have equal hash codes, or a HashSet keeps both
        check(a.hashCode() == sameIsbn.hashCode(), "exercise 2 hashCode");
        Set<Book> shelf = new HashSet<>();
        shelf.add(a);
        shelf.add(sameIsbn);
        shelf.add(other);
        check(shelf.size() == 2, "exercise 2 HashSet removes the duplicate");

        // 3. toString: "Head First Java (978-0596009205)"
        check(a.toString().equals("Head First Java (978-0596009205)"), "exercise 3 toString");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

class Book {
    private final String isbn;
    private final String title;
    private final int year;

    Book(String isbn, String title, int year) {
        this.isbn = isbn;
        this.title = title;
        this.year = year;
    }

    // TODO exercise 1: override equals(Object) - compare the ISBN only
    // TODO exercise 2: override hashCode() - use the SAME field(s) as equals
    // TODO exercise 3: override toString()
}
