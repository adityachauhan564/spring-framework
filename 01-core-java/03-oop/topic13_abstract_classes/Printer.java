package topic13_abstract_classes;

/*
 * Topic    : Abstract classes
 * Key idea : An abstract class is an incomplete class. You can NOT create it with 'new'.
 *            It is like a fill-in-the-blanks form:
 *            - some parts are already written - shared code every child gets (printTwice)
 *            - some parts are left blank - abstract methods that every child MUST fill in (print)
 * Read     : Printer -> ConsolePrinter -> AbstractClassDemo
 */
public abstract class Printer {

    public abstract void print(String message);   // no body here: each child class decides HOW to print

    public void printTwice(String message) {       // shared code, written once, used by every child
        print(message);
        print(message);
    }
}
