package topic08_classes_and_objects;

/*
 * Exercises for topic 08.
 * How to use:
 *   - Complete the two classes written BELOW this one: Book and Counter.
 *     Fill in every "TODO".
 *   - Then run:  java -cp out topic08_classes_and_objects.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 *
 * (One file can have many classes, but only the class with the same name as the file can be public.)
 */
public class Exercises {

    public static void main(String[] args) {
        // 1. Book
        Book book = new Book("Head First Java", "Kathy Sierra", 720);
        check(book.getTitle().equals("Head First Java"), "exercise 1 getTitle");
        check(book.getPages() == 720, "exercise 1 getPages");
        check(book.isLong() && !new Book("Short", "Me", 120).isLong(), "exercise 1 isLong");
        check(book.toString().equals("Head First Java by Kathy Sierra (720 pages)"), "exercise 1 toString");

        // 2. Counter: each object keeps its OWN count (clicks and visits don't mix)
        Counter clicks = new Counter();
        Counter visits = new Counter();
        clicks.increment();
        clicks.increment();
        visits.increment();
        check(clicks.getCount() == 2 && visits.getCount() == 1, "exercise 2 increment");
        clicks.reset();
        check(clicks.getCount() == 0 && visits.getCount() == 1, "exercise 2 reset");

        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 1. A Book has a title, an author and a number of pages. All three are set once, in the constructor.
//    isLong() returns true if the book has more than 300 pages.
//    toString() returns: "<title> by <author> (<pages> pages)"
class Book {
    // TODO: private final fields

    Book(String title, String author, int pages) {
        // TODO: store the parameters in the fields (use this.)
    }

    String getTitle() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    int getPages() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    boolean isLong() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }
}

// 2. A Counter, like a tally counter at a temple gate:
//    starts at 0, increment() adds 1, reset() takes it back to 0.
class Counter {
    // TODO: a private field

    void increment() {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    void reset() {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    int getCount() {
        throw new UnsupportedOperationException("TODO exercise 2");
    }
}
