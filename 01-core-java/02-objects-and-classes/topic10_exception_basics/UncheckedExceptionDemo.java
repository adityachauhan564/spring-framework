package topic10_exception_basics;

/*
 * Topic    : Unchecked (runtime) exceptions
 * Key idea : The compiler does NOT force you to handle these.
 *            Examples: NullPointerException, ArithmeticException (divide by zero).
 *            They usually mean there is a bug in YOUR code.
 *            So the right fix is to check the value first and stop the problem from happening -
 *            not to catch it after it happens.
 *            Like checking your wallet before ordering, instead of arguing at the billing counter.
 * Run      : java -cp out topic10_exception_basics.UncheckedExceptionDemo
 */
public class UncheckedExceptionDemo {

    public static void main(String[] args) {
        String name = null;

        // 1. The good way: check first, so the exception never happens
        printLengthSafely(name);
        printLengthSafely("Aditya");

        // 2. This also works, but catching a NullPointerException only hides the bug
        try {
            System.out.println(name.length());     // name is null, so this crashes
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: string was null");
        }
    }

    private static void printLengthSafely(String text) {
        if (text == null) {                        // check before using it
            System.out.println("String can't be null");
            return;                                // leave the method early
        }
        System.out.println("Length of '" + text + "' is " + text.length());
    }
}
