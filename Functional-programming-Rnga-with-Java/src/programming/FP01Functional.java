package programming;

import java.util.List;

public class FP01Functional {

	
	  public static void main(String[] args) {
	  
	  printAllNumbersInFunctional(List.of(12,9,6,13,19,27,31));
	  
	  }

		/*
		 * private static void print(int number) { System.out.println(number); }
		 */

	private static void printAllNumbersInFunctional(List<Integer> numbers) {
		// what to do ??

		numbers.stream().forEach(System.out::println);// method reference

		System.out.println("Here are the Even Numbers");

		numbers.stream().filter(e -> e % 2 == 0) // Lambda Expression
				.forEach(System.out::println); // method reference

	}

}
