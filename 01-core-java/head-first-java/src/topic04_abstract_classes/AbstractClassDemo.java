package topic04_abstract_classes;

/*
 * Run      : java -cp out topic04_abstract_classes.AbstractClassDemo
 * Key idea : the variable's type is the abstract class, the object is the
 *            subclass. Java calls the subclass's print() at runtime (polymorphism).
 * Try this : write an UpperCasePrinter and swap it in without changing the other lines.
 */
public class AbstractClassDemo {

    public static void main(String[] args) {
        // Printer p = new Printer();   // compile error: Printer is abstract
        Printer printer = new ConsolePrinter();

        printer.print("Hello");
        printer.printTwice("Inherited method");
    }
}
