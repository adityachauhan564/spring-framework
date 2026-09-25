package programming;

import java.util.List;

public class FP01Structured {
	
	public static void main(String[] args) {
		
		printAllNumbersInStructured(List.of(12,9,6,13,19,27,31));
		
	}

	private static void printAllNumbersInStructured(List<Integer> numbers) {
		// how to loop the numbers ? 
		for(int number : numbers) {
			System.out.println(number);
		}
	}

}
