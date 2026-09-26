package topic05_interfaces_and_dependency_injection;

public class CreditCardPayment implements PaymentService {

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}
