package topic08_classes_and_objects.solutions;

// Solutions for topic08_classes_and_objects/Exercises.java
public class ExercisesSolution {

    public static void main(String[] args) {
        Book book = new Book("Head First Java", "Kathy Sierra", 720);
        check(book.getTitle().equals("Head First Java"), "exercise 1 getTitle");
        check(book.getPages() == 720, "exercise 1 getPages");
        check(book.isLong() && !new Book("Short", "Me", 120).isLong(), "exercise 1 isLong");
        check(book.toString().equals("Head First Java by Kathy Sierra (720 pages)"), "exercise 1 toString");

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

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

class Book {
    private final String title;       // final: set once, in the constructor
    private final String author;
    private final int pages;

    Book(String title, String author, int pages) {
        this.title = title;           // this.title = the field, title = the parameter
        this.author = author;
        this.pages = pages;
    }

    String getTitle() {
        return title;
    }

    int getPages() {
        return pages;
    }

    boolean isLong() {
        return pages > 300;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (" + pages + " pages)";
    }
}

class Counter {
    private int count;                // a field starts at 0; each Counter object has its own

    void increment() {
        count++;
    }

    void reset() {
        count = 0;
    }

    int getCount() {
        return count;
    }
}
