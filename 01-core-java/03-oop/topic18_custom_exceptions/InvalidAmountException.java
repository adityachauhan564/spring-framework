package topic18_custom_exceptions;

/*
 * An UNCHECKED exception (it extends RuntimeException).
 * Paying 0 or a negative amount is a mistake in the calling code (bad input),
 * so the compiler does not force anyone to catch it.
 */
public class InvalidAmountException extends RuntimeException {

    public InvalidAmountException(double amount) {
        super("Amount must be positive, got " + amount);
    }
}
