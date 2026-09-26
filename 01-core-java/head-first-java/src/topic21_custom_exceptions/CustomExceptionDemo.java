package topic21_custom_exceptions;

/*
 * Run      : java -cp out topic21_custom_exceptions.CustomExceptionDemo
 * Key idea : catch the specific exceptions you can handle; 'finally' always runs.
 * Try this : wrap the exception - throw new RuntimeException("Payment failed", e) - and
 *            print e.getCause().
 */
public class CustomExceptionDemo {

    public static void main(String[] args) {
        Wallet wallet = new Wallet(100);

        double[] payments = {30, 500, -5};
        for (double amount : payments) {
            try {
                wallet.pay(amount);
                System.out.println("Paid " + amount + ", balance " + wallet.getBalance());
            } catch (InsufficientBalanceException e) {
                System.out.println(e.getMessage() + " -> top up at least " + e.getShortBy());
            } catch (InvalidAmountException e) {
                System.out.println("Bad input: " + e.getMessage());
            } finally {
                System.out.println("  (finally: attempt for " + amount + " finished)");
            }
        }
    }
}
