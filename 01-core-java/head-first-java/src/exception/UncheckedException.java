package exception;

public class UncheckedException {
	
	public static void main(String[] args) {
		//String name="Aditya";
		String name=null;
		printLength(name);
	}
	
	private static void printLength(String myString) {
		// unchecked exception
		try {
		System.out.println(myString.length());
		}
		catch(NullPointerException npe) {
			System.out.println("String Can't be null");
		}
	}

}
