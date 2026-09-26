package topic04_abstract_classes;

/* A concrete subclass: it must implement every abstract method of Printer. */
public class ConsolePrinter extends Printer {

    @Override
    public void print(String message) {
        System.out.println("[console] " + message);
    }
}
