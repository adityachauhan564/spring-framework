package topic13_abstract_classes;

/*
 * A normal (complete) child class of Printer.
 * It MUST fill in every abstract method of Printer, otherwise it won't compile.
 */
public class ConsolePrinter extends Printer {

    @Override
    public void print(String message) {
        System.out.println("[console] " + message);
    }
}
