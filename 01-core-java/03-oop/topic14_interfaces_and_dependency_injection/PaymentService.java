package topic14_interfaces_and_dependency_injection;

/*
 * Topic    : Interfaces and dependency injection
 * Key idea : An interface is a promise (a contract). It only says WHAT can be done, not HOW.
 *            This one says: "anything that is a PaymentService can pay(amount)".
 *            Like the "UPI accepted" sticker at a shop - the shop doesn't care if you use
 *            GPay, PhonePe or Paytm. Any app that keeps the UPI promise will work.
 *            Code written against the interface works with EVERY class that implements it.
 * Read     : PaymentService -> UPIPayment / CreditCardPayment -> OrderService -> Main
 */
public interface PaymentService {

    void pay(double amount);        // no body - each payment class writes its own
}
