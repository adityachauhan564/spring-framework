package Java_Full_stack_Basics_11_Hours_Youtube;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


// List, Map, Sets, Optional ( to avoid null pointer exception )

//Since NullPointer Exception is the most common error in java
public class Collections_important {
	
	public static void main(String[] args) {
		/*
		 * List<String> list=new ArrayList<>();
		 * 
		 * list.add("Roshan"); list.add("Chauhan");
		 * 
		 * System.out.println(list);
		 */
		
		
		Map<String,Integer> mp=new HashMap<>();
		
		mp.put("Aditya R. Chauhan ", 01);
		mp.put("Roshan", 9);
		mp.put(null, null);
		mp.put("Goal Package in Lakhs/month ",  2);
		
		for(Map.Entry<String, Integer> data : mp.entrySet()) {  // for generic <String, Integer> will be used 
			
			System.out.print(data.getKey());
			System.out.print(data.getValue());
			
			System.out.println("-----");
		}
		
		//Optional 
		
		Optional<Integer> mpOfMe=Optional.ofNullable(mp.get("Rohan"));
		System.out.println(mpOfMe);
		if(mpOfMe.isPresent()) {
			
		}else {
			System.out.println(" mp is not Presenet");
		}
		
		//System.out.println(mp);
	
	}
           
}
