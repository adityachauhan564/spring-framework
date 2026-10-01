package topic14_interfaces_and_dependency_injection;

// One way to keep the PaymentService promise: pay using UPI
public class UPIPayment implements PaymentService {

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using UPI");
    }
}
