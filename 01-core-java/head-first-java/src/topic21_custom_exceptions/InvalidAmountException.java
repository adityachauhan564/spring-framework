package topic21_custom_exceptions;

/* Unchecked: a programming mistake (bad input), so the compiler doesn't force a catch. */
public class InvalidAmountException extends RuntimeException {

    public InvalidAmountException(double amount) {
        super("Amount must be positive, got " + amount);
    }
}
