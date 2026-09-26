package topic05_interfaces_and_dependency_injection;

/*
 * Topic    : Interfaces and dependency injection
 * Key idea : an interface is a contract - "anything that can pay(amount)".
 *            Code that depends on the contract works with every implementation.
 * Read     : PaymentService -> UPIPayment / CreditCardPayment -> OrderService -> Main
 */
public interface PaymentService {

    void pay(double amount);
}
