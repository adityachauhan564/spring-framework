package topic18_custom_exceptions;

/*
 * Topic    : Custom exceptions (making your own exception classes)
 * Key idea : Java's built-in exceptions are general. Your own exception can say exactly
 *            what went wrong in YOUR app - like "Insufficient balance" in a wallet app.
 *            - extend Exception        -> a CHECKED exception (the caller is forced to handle it)
 *            - extend RuntimeException -> an UNCHECKED exception (handling it is optional)
 *            You can also keep useful data inside it, like HOW MUCH money is short.
 * Read     : InsufficientBalanceException -> InvalidAmountException -> Wallet -> CustomExceptionDemo
 */
public class InsufficientBalanceException extends Exception {

    private final double shortBy;           // how much more money is needed

    public InsufficientBalanceException(double shortBy) {
        super("Insufficient balance: short by " + shortBy);   // this text is what getMessage() returns
        this.shortBy = shortBy;
    }

    public double getShortBy() {
        return shortBy;
    }
}
