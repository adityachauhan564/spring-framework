package com.junit;

public class MyMaths {
	
	// {2,3,5,6} =2+3+5+6
	
	public int calsum(int [] numbers){
		int sum=0;
		
		for(int number: numbers) {
			sum+=number;
		}
		
		return sum;
		
	}

}
