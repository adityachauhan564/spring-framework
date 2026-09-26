package Oops_Abstraction_Realworld_Example;

public class Main {
	public static void main(String[] args) {
		
		PaymentService payment=new UPIPayment(); // Or Credit Card Payment
		
		OrderService orderService=new OrderService(payment);
		
		        orderService.placeOrder(1000);
				
			}
	
	}

