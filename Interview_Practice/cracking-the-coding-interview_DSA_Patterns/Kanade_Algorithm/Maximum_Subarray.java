package Kanade_Algorithm;


import java.util.*;

public class Maximum_Subarray {
	
	public static int findMaxSubArraySum(int [] arr) {
		
		int max_sum=arr[0];
		
		int res = 0;
		
		for(int i=1;i<arr.length-1;i++) {
			
			int m1=arr[i];
			
			int m2=arr[i]+arr[i+1];
			
			int bestMax=Math.max(m1,m2 );
			
			 res=Math.max(max_sum, bestMax);
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		
		int [] arr= {-2,1,-3,4,-1,2,1,-5,4};
		
		int res=findMaxSubArraySum(arr);
		
		System.out.println(res);
	}

}
