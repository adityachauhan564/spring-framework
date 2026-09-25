package Chapter_2;

public class BottleSong {
	
	public static void main(String[] args) {
		String word="Bottles";
	    int bottlesNum=1;

//    Random Generation
//	    double r1=Math.random();
//	    double r2=Math.random();
//	    
//	    System.out.println(r1+r2);
	   while(bottlesNum>0) {
		   if(bottlesNum==1) {
		   word="bottle";
		   }
		   
		   System.out.println(bottlesNum + " green " + word + ", hanging on the wall");
		   System.out.println(bottlesNum + " green " + word + ", hanging on the wall");
		   System.out.println("And if one green bottle should accidentally fall,");
		   bottlesNum-=1;
		   
		   if (bottlesNum > 0) {
			   System.out.println("There'll be " + bottlesNum +
			   " green " + word + ", hanging on the wall");
			   } else {
			   System.out.println("There'll be no green bottles, hanging on the wall");
			   } 
	   }
	}

}
