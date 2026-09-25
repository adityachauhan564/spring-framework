package Oops_Abstraction_Realworld_Example;

public class UPIPayment implements PaymentService {

	public void pay(double amount) {
		System.out.println("Paid "+amount+" using UPI");
	}
}
