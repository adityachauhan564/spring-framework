package oops_abstraction_realworld_example;

public class CreditCardPayment implements PaymentService {

	public void pay(double amount) {
		System.out.println("Paid"+amount+" using Credit Card");
	}
}
