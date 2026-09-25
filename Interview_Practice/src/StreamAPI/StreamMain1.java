package StreamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMain1 {
	public static void main(String[] args) {
		
		
		//create a list and filter all even numbers from list
		
		List<Integer> list1=List.of(2,4,50,21,22,10); 
		//cant't update
		
		List<Integer> list2=new ArrayList<>(); // could be updated 
		list2.add(2);
		list2.add(5);
		list2.add(10);
		list2.add(9);
		list2.add(7);
		
		
		List<Integer> list3=Arrays.asList(23,12,9,7,5); //can't be updated 
		
		//list1 find even numbers
		//using stream 
//		Stream<Integer> stream = list1.stream();
//		List<Integer> newList = stream.filter(i->i%2==0).collect(Collectors.toList());
//		System.out.println(newList);
		
		// More Better use of Stream api
		
		List<Integer> newList1 = list1.stream().filter(i->i%2==0).collect(Collectors.toList());
		System.out.println(newList1);
		
		//get Greater than 10c
		List<Integer> new10PList = list1.stream().filter(i->i>10).collect(Collectors.toList());
		System.out.println(new10PList);
		
	}

}
