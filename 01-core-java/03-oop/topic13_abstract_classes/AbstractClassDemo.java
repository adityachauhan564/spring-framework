package topic13_abstract_classes;

/*
 * Run      : java -cp out topic13_abstract_classes.AbstractClassDemo
 * Key idea : The variable's type is the abstract class (Printer),
 *            but the real object is the child class (ConsolePrinter).
 *            While running, Java calls the child's print() - this is polymorphism again.
 * Try this : Write an UpperCasePrinter and use it here, without changing any other line.
 */
public class AbstractClassDemo {

    public static void main(String[] args) {
        // Printer p = new Printer();   // compile error: you can't create an abstract class
        Printer printer = new ConsolePrinter();

        printer.print("Hello");
        printer.printTwice("Inherited method");     // printTwice comes from Printer, but it calls ConsolePrinter's print()
    }
}
