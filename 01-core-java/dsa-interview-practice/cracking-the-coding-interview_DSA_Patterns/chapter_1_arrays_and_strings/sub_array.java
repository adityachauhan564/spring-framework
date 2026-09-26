package chapter_1_arrays_and_strings;

public class sub_array {
	
	/*
	 * Given an integer array nums, find the contiguous subarray (containing at
	 * least one number) which has the largest product, and return that product.
	 */
	
	public int mxprdct(int [] nums) {
		
		int mxP=nums[0];
		
		int minP=nums[0];
		
		int result=nums[0];
		
		int n=nums.length;
		
		for(int i=1;i<n;i++) {
			
			int temp=mxP;
			
			mxP=Math.max(nums[i], Math.max(mxP * nums[i],minP*nums[i]));
			
			minP=Math.min(nums[i],Math.min(temp*nums[i],minP*nums[i]));
			
			result=Math.max(result, mxP);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		//input - {2, 3, -2, 4}
		
		
//	[2],[2,3], [2,3,-2], [3,-2,4]
		
		
			
		int [] nums = {-5};
		
		sub_array sol=new sub_array();
		
		int res=sol.mxprdct(nums);
		
		System.out.println(res);
		
		
	}

}
