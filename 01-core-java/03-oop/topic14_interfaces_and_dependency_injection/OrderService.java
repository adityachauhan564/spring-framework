package topic14_interfaces_and_dependency_injection;

/*
 * OrderService only knows "some payment method" (the PaymentService interface).
 * It does not care if it is UPI or Credit Card.
 *
 * Think of a shopkeeper: he just says "pay karo" - you choose
 * GPay or card. The shop does not change.
 *
 * We pass the payment method from outside, through the constructor.
 * This is called "constructor injection" (a type of dependency injection).
 * OrderService never writes 'new UPIPayment()' itself.
 * Spring does this for us automatically later (stage 02).
 */
public class OrderService {

    private final PaymentService paymentService;     // only the interface type - not UPI, not Card

    // whoever creates OrderService decides which payment method to hand over
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder(double amount) {
        paymentService.pay(amount);                  // runs UPI's or Card's pay(), whichever was passed in
        System.out.println("Order placed successfully");
    }
}
