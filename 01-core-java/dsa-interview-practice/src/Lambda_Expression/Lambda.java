package Lambda_Expression;

public class Lambda {
	
	
	/* <Lambda Expression >  ********************{ It can be only used with Functional Interface } 
	 * 
	 * Lambda is anonymous function. 
	 *  > No name
	 *  > No Modifier
	 *  > No Return Type
	 * <Benefits of Lambda function >
	 * >Reduce lines of code
	 * >Sequential and Parallel Execution support by passing behaviour as an argument in methods.
	 * >To call APIs very Effectively.
	 * >To write more Readable, Maintainable and Concise Code.
	 * 
	 * just use Arrow(-->) and do work
	 * 
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Here is the Lambda Expression");
       
		
		/*Using Out Interface with help of the lambda
		 
		 * don't forget to use ;  (semicolon)
		 */
		
		MyInterface i=()->System.out.println(" This is the first time I am using Lambda Expression ");
		
		i.sayHello();
		
		// Add sum of two 
		
		SumInter sumInter=(a,b) ->(a+b); 
		
		System.out.println(sumInter.sum(1,6));
		System.out.println(sumInter.sum(0, 1));
		
		//find length
		
		LengthInter lengthInter=(str -> str.length());
	    System.out.println("The length of the String is >>>>>>>>> "+lengthInter.getLength("Aditya Chauhan"));
//       MyInterfaceImpl myInterfaceImpl = new MyInterfaceImpl();
//		
//		myInterfaceImpl.sayHello();   
		
		
		
//		MyInterface i=new MyInterface() {
//
//			@Override
//			public void sayHello() {
//				// TODO Auto-generated method stub
//				
//				System.out.println("This is my first Anonymous Class");
//				
//			}	
//	};
//
//	i.sayHello();
//	
//	MyInterface i2=new MyInterface() {
//
//		@Override
//		public void sayHello() {
//			// TODO Auto-generated method stub
//			System.out.println("This is my Second Anonymous Class ***** ");
//			
//		}
//		
//	};
//	
//	i2.sayHello();
		
		
}
}
