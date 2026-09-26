package topic06_exceptions;

/*
 * Topic    : Unchecked (runtime) exceptions
 * Key idea : the compiler does NOT force you to handle these
 *            (NullPointerException, ArithmeticException, ...). They are
 *            usually bugs - prevent them with a check rather than catching them.
 * Run      : java -cp out topic06_exceptions.UncheckedExceptionDemo
 */
public class UncheckedExceptionDemo {

    public static void main(String[] args) {
        String name = null;

        // 1. Preferred: check first, so the exception never happens
        printLengthSafely(name);
        printLengthSafely("Aditya");

        // 2. Possible, but catching a NullPointerException hides a bug
        try {
            System.out.println(name.length());
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: string was null");
        }
    }

    private static void printLengthSafely(String text) {
        if (text == null) {
            System.out.println("String can't be null");
            return;
        }
        System.out.println("Length of '" + text + "' is " + text.length());
    }
}
