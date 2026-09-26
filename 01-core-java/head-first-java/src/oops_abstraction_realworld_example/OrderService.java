package oops_abstraction_realworld_example;
// we used abstraction in the business logic
public class OrderService {
	
	private final PaymentService paymentService;

	//constructor 
	public OrderService(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}

	public void placeOrder(double amount) {
		paymentService.pay(amount);
		System.out.println("Order Placed Successfully");
	}
}
