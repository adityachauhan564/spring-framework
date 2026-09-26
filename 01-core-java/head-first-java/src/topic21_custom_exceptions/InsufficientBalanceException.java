package topic21_custom_exceptions;

/*
 * Topic    : Custom exceptions
 * Key idea : extend Exception for a CHECKED exception (callers must handle it) or
 *            RuntimeException for an UNCHECKED one. Carry useful data in fields.
 * Read     : InsufficientBalanceException -> InvalidAmountException -> Wallet -> CustomExceptionDemo
 */
public class InsufficientBalanceException extends Exception {

    private final double shortBy;

    public InsufficientBalanceException(double shortBy) {
        super("Insufficient balance: short by " + shortBy);   // becomes getMessage()
        this.shortBy = shortBy;
    }

    public double getShortBy() {
        return shortBy;
    }
}
