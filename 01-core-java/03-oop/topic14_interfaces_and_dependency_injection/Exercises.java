package topic14_interfaces_and_dependency_injection;

/*
 * Exercises for topic 14. Complete the two classes below this one, then run:
 *   java -cp out topic14_interfaces_and_dependency_injection.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 *
 * PaymentService and OrderService are the ones from this topic - DON'T change them.
 */
public class Exercises {

    public static void main(String[] args) {
        // 1. A new payment type, used by OrderService without changing OrderService
        WalletPayment wallet = new WalletPayment(1000);
        new OrderService(wallet).placeOrder(300);
        check(wallet.getBalance() == 700, "exercise 1 the wallet pays");
        try {
            new OrderService(wallet).placeOrder(5000);
            check(false, "exercise 1 must refuse to pay more than the balance");
        } catch (IllegalStateException expected) {
            check(wallet.getBalance() == 700, "exercise 1 a refused payment must not change the balance");
        }

        // 2. A fake payment for TESTS: it records the amounts instead of moving money.
        //    Dependency injection is what makes swapping it in possible.
        RecordingPayment fake = new RecordingPayment();
        OrderService service = new OrderService(fake);
        service.placeOrder(10);
        service.placeOrder(25);
        check(fake.count() == 2 && fake.total() == 35, "exercise 2 RecordingPayment");

        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 1. Pays from a balance; throws IllegalStateException when the balance is too low.
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

// 2. Remembers how many payments were made and their total.
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
