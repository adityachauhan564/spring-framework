package exception;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Checked_Exception {
	public static void main(String[] args)  {
		readFile("myFile.txt");
	}
	
	
	/*private static void readFile(String fileName) {
		 
		   FileReader reader=new FileReader(fileName);*/
		   
		   // OR 
	
	  private static void readFile(String fileName) {
	  
       try { FileReader reader=new FileReader(fileName); //checked exception
	  
	  } catch(FileNotFoundException fnfe) {
	  System.out.println("File doesn't exists "); } }
	 

   }
//you need the try catch to handle the exception