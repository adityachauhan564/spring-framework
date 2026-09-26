package programming;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

//to counter null pointer Exception
public class PlayingWithOptional {
	
	public static void main(String[] args) {
		
	
	List<String> fruits=List.of("apple","banana","mango");
	
	Predicate<? super String> predicate=fruite-> fruite.startsWith("b");
	
			Optional<String> optional=fruits.stream().filter(predicate).findFirst();
			
		  
			System.out.println(optional);
			System.out.println(optional.isEmpty());
			System.out.println(optional.isPresent());
			System.out.println(optional.get());
	
	}
}
