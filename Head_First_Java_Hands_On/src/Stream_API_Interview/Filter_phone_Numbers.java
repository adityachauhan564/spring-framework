package Stream_API_Interview;

import java.util.*;
import java.util.stream.Collectors;

public class Filter_phone_Numbers {
	
	public static void main(String[] args) {
		
		List<String> data=Arrays.asList("Call: 98765-43210",
			    "Office: +91 9988776655",
			    "Fake: 123-45",
			    "Alt: 9123456780",
			    null,
			    "Duplicate: 9876543210",
			    " ",
			    "Space format: 98765 43210");
		
		
		List<String> cleanedList=data.stream()
				.filter(Objects :: nonNull) //remove null
				
				.map(String :: trim) //trim space 
				
				.filter(str ->!str.isEmpty()) //remove empty string
				
				.map(str -> str.replaceAll("[^0-9]", "")) //keep only digit 
				
				.map( str -> {
					
					if(str.length()==12 && str.startsWith("91"))
						return str.substring(2);      // remove country code
					return str;
				})
				.filter(str ->str.length()==10) //keep only valid 10 digits
				
				.distinct()
				
				.collect(Collectors.toList());
				
		System.out.println(cleanedList);
		
	}

}
