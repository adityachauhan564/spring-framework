package topic14_interfaces_and_dependency_injection.solutions;

import topic14_interfaces_and_dependency_injection.OrderService;
import topic14_interfaces_and_dependency_injection.PaymentService;

// Solutions for topic14_interfaces_and_dependency_injection/Exercises.java
public class ExercisesSolution {

    public static void main(String[] args) {
        WalletPayment wallet = new WalletPayment(1000);
        new OrderService(wallet).placeOrder(300);
        check(wallet.getBalance() == 700, "exercise 1 the wallet pays");
        try {
            new OrderService(wallet).placeOrder(5000);
            check(false, "exercise 1 must refuse to pay more than the balance");
        } catch (IllegalStateException expected) {
            check(wallet.getBalance() == 700, "exercise 1 a refused payment must not change the balance");
        }

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

// OrderService only knows PaymentService, so it accepts this class without any change
class WalletPayment implements PaymentService {
    private double balance;

    WalletPayment(double openingBalance) {
        this.balance = openingBalance;
    }

    @Override
    public void pay(double amount) {
        if (amount > balance) {
            throw new IllegalStateException("Wallet balance " + balance + " is too low for " + amount);
        }
        balance -= amount;
        System.out.println("Paid " + amount + " from the wallet");
    }

    double getBalance() {
        return balance;
    }
}

// A test double: in a unit test you check what WOULD have been paid, without paying
class RecordingPayment implements PaymentService {
    private int count;
    private double total;

    @Override
    public void pay(double amount) {
        count++;
        total += amount;
    }

    int count() {
        return count;
    }

    double total() {
        return total;
    }
}
