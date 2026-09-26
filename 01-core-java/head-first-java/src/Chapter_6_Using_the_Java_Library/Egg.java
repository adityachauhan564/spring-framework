package Chapter_6_Using_the_Java_Library;

import java.util.ArrayList;

public class Egg {
	
	public static void main(String[] args) {
		
		/*
		 * Don’t worry about this new <Egg> angle-bracket syntax right now; it just
		 * means “make this a list of Egg objects.”
		 */
		
		ArrayList<Egg> myList=new ArrayList<Egg>();
		//put something in it
		Egg egg1=new Egg();
		
		myList.add(egg1);
		
		Egg egg2=new Egg();
		
		myList.add(egg2);
		
		int theSize=myList.size();
		//Find out how many things are in it
		System.out.println(theSize);
		
		//Find out if it contains something
		
		boolean isIN=myList.contains(egg1);
		
		System.out.println(myList.contains(egg1));
	}

}
