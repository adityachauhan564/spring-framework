package topic14_interfaces_and_dependency_injection;

// Another way to keep the same PaymentService promise: pay using a credit card
public class CreditCardPayment implements PaymentService {

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}
