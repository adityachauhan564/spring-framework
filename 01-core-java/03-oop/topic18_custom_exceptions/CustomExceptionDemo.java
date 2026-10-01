package topic18_custom_exceptions;

/*
 * Run      : java -cp out topic18_custom_exceptions.CustomExceptionDemo
 * Key idea : Catch each specific exception you know how to handle, and handle each one in its own way.
 *            'finally' runs every time, no matter what happened.
 * Try this : Wrap the exception like this - throw new RuntimeException("Payment failed", e) -
 *            and then print e.getCause() to see the original problem.
 */
public class CustomExceptionDemo {

    public static void main(String[] args) {
        Wallet wallet = new Wallet(100);

        double[] payments = {30, 500, -5};      // one good payment, one too big, one negative
        for (double amount : payments) {
            try {
                wallet.pay(amount);
                System.out.println("Paid " + amount + ", balance " + wallet.getBalance());
            } catch (InsufficientBalanceException e) {
                // our exception carries the shortfall, so we can give a helpful message
                System.out.println(e.getMessage() + " -> top up at least " + e.getShortBy());
            } catch (InvalidAmountException e) {
                System.out.println("Bad input: " + e.getMessage());
            } finally {
                System.out.println("  (finally: attempt for " + amount + " finished)");
            }
        }
    }
}
