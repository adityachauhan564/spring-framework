package topic14_interfaces_and_dependency_injection;

/*
 * Exercises for topic 14.
 * How to use:
 *   - Complete the two classes written BELOW this one. Fill in every "TODO".
 *   - Then run:  java -cp out topic14_interfaces_and_dependency_injection.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 *
 * Use the PaymentService and OrderService from this topic as they are - DON'T change them.
 */
public class Exercises {

    public static void main(String[] args) {
        // 1. A new payment type (like a Paytm wallet). OrderService must use it without any change.
        WalletPayment wallet = new WalletPayment(1000);
        new OrderService(wallet).placeOrder(300);
        check(wallet.getBalance() == 700, "exercise 1 the wallet pays");
        try {
            new OrderService(wallet).placeOrder(5000);
            check(false, "exercise 1 must refuse to pay more than the balance");
        } catch (IllegalStateException expected) {
            check(wallet.getBalance() == 700, "exercise 1 a refused payment must not change the balance");
        }

        // 2. A FAKE payment, only for testing: it writes down the amounts instead of moving real money.
        //    We can swap it in only because OrderService takes its payment from outside (dependency injection).
        RecordingPayment fake = new RecordingPayment();
        OrderService service = new OrderService(fake);
        service.placeOrder(10);
        service.placeOrder(25);
        check(fake.count() == 2 && fake.total() == 35, "exercise 2 RecordingPayment");

        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 1. Pays from a balance. Throws IllegalStateException when the balance is not enough.
class WalletPayment implements PaymentService {
    WalletPayment(double openingBalance) {
        // TODO
    }

    @Override
    public void pay(double amount) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    double getBalance() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }
}

// 2. Remembers how many payments were made, and their total amount.
class RecordingPayment implements PaymentService {
    @Override
    public void pay(double amount) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    int count() {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    double total() {
        throw new UnsupportedOperationException("TODO exercise 2");
    }
}
