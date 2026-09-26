package topic04_abstract_classes;

/*
 * Topic    : Abstract classes
 * Key idea : an abstract class can't be created with 'new'. It can hold
 *            shared code (printTwice) and force subclasses to fill in the
 *            abstract parts (print).
 * Read     : Printer -> ConsolePrinter -> AbstractClassDemo
 */
public abstract class Printer {

    public abstract void print(String message);   // no body: each subclass decides

    public void printTwice(String message) {       // shared code, written once
        print(message);
        print(message);
    }
}
