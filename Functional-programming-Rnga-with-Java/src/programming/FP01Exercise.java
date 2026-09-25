package programming;

import java.util.List;

public class FP01Exercise {

	 public static void main(String[] args) {
		  
//		  printAllEvenInFunctional(List.of(12,9,6,13,19,27,31));
		  
		  
		List<String> courses =List.of("Spring","Spring Boot","API","Microservices","System Design","Dockor","Kubernities");
		
//		courses.stream()
//		.forEach(System.out :: println);
		
		courses.stream()
		.filter(course->course.contains("Spring"))
		.forEach(System.out :: println);

		  System.out.println("Here are the course having more than 4 letters");
		courses.stream()
		.filter(course->course.length() >=4)
		.forEach(System.out :: println);
		  }



		private static void printAllEvenInFunctional(List<Integer> numbers) {
			// what to do ??

			//numbers.stream().forEach(System.out::println);// method reference

			System.out.println("Here are the Odd Numbers -----");

			numbers.stream().filter(e -> e % 2 != 0) // Lambda Expression
					.forEach(System.out::println); // method reference
			
			//Exercise
			
		
}

}