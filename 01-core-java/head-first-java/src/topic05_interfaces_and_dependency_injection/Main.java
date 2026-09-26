package topic05_interfaces_and_dependency_injection;

/*
 * Run      : java -cp out topic05_interfaces_and_dependency_injection.Main
 * Key idea : the same OrderService code runs with two different payment
 *            methods. Only the object we pass in changes.
 * Try this : add a WalletPayment class - OrderService needs no change.
 */
public class Main {

    public static void main(String[] args) {
        OrderService upiOrder = new OrderService(new UPIPayment());
        upiOrder.placeOrder(1000);

        OrderService cardOrder = new OrderService(new CreditCardPayment());
        cardOrder.placeOrder(2500);
    }
}
