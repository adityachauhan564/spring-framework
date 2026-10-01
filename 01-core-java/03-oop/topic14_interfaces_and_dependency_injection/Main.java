package topic14_interfaces_and_dependency_injection;

/*
 * Run      : java -cp out topic14_interfaces_and_dependency_injection.Main
 * Key idea : The SAME OrderService code works with two different payment methods.
 *            The only thing that changes is the object we pass in.
 * Try this : Add a WalletPayment class - OrderService will need no change at all.
 */
public class Main {

    public static void main(String[] args) {
        OrderService upiOrder = new OrderService(new UPIPayment());          // inject UPI
        upiOrder.placeOrder(1000);

        OrderService cardOrder = new OrderService(new CreditCardPayment());  // inject Credit Card
        cardOrder.placeOrder(2500);
    }
}
