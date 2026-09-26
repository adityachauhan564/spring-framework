package Oops_Abstraction_Realworld_Example;

public class CreditCardPayment implements PaymentService {

	public void pay(double amount) {
		System.out.println("Paid"+amount+" using Credit Card");
	}
}
