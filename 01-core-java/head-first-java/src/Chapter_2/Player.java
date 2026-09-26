package Chapter_2;

public class Player {

	public int number=0;

	public void guess() {
		
		number = (int) (Math.random() * 10);
        System.out.println("I’m guessing " + number);
		
	}

}
