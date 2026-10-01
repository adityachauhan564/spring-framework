package topic15_equals_and_hashcode.solutions;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// Answers for topic15_equals_and_hashcode/Exercises.java
public class ExercisesSolution {

    public static void main(String[] args) {
        Book a = new Book("978-0596009205", "Head First Java", 2005);
        Book sameIsbn = new Book("978-0596009205", "Head First Java (2nd printing)", 2006);
        Book other = new Book("978-1491910740", "Head First Java 3e", 2022);

        check(a.equals(sameIsbn) && !a.equals(other), "exercise 1 equals");
        check(!a.equals(null) && !a.equals("978-0596009205"), "exercise 1 equals with null / another type");

        check(a.hashCode() == sameIsbn.hashCode(), "exercise 2 hashCode");
        Set<Book> shelf = new HashSet<>();
        shelf.add(a);
        shelf.add(sameIsbn);
        shelf.add(other);
        check(shelf.size() == 2, "exercise 2 HashSet removes the duplicate");

        check(a.toString().equals("Head First Java (978-0596009205)"), "exercise 3 toString");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
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

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;                          // same object - obviously equal
        }
        if (!(other instanceof Book book)) {       // not a Book (this is also false for null)
            return false;
        }
        return isbn.equals(book.isbn);            // what makes a book unique is its ISBN
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);                // exactly the same field(s) that equals() uses
    }

    @Override
    public String toString() {
        return title + " (" + isbn + ")";
    }
}
