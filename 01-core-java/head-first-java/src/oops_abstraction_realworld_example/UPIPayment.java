package oops_abstraction_realworld_example;

public class UPIPayment implements PaymentService {

	public void pay(double amount) {
		System.out.println("Paid "+amount+" using UPI");
	}
}
