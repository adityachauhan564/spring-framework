package Lambda_Expression;

import java.util.Iterator;

public class ThreadDemo {
	public static void main(String[] args) {
		
		//first thread : Thread John
		
		Runnable thread1=()-> {
			
			//this is body of the thread
			//stuff
		 for (int i = 1; i <=10; i++) {
			System.out.println("Value of i is "+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		};
		Thread t=new Thread(thread1);
		t.setName("John");
		t.start();
		
		
		//Thread 2
		Runnable thread2=()->{
			try {
			for (int i = 1; i < 10; i++) {
				System.out.println(i*2);
				Thread.sleep(2000);
			}
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		};
		Thread t2=new Thread(thread2);
		t2.start();
		
		
	}

}
