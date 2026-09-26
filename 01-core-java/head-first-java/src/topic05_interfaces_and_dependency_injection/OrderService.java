package topic05_interfaces_and_dependency_injection;

/*
 * Business logic that only knows the PaymentService interface.
 * The payment method is passed in through the constructor
 * (constructor injection) - OrderService never calls 'new UPIPayment()'.
 * This is exactly what Spring automates later (stage 02).
 */
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder(double amount) {
        paymentService.pay(amount);
        System.out.println("Order placed successfully");
    }
}
