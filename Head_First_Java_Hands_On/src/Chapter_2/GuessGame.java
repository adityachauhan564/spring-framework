package Chapter_2;

public class GuessGame {
	
	Player p1;
    Player p2; //3 Players 
    Player p3;
    
    public void startGame() { 
    	
    	p1= new Player();
    	p2=new Player();  //Creating 3 players objects and assign them to the
    	                 //3 player instance player
    	p3=new Player();
    	
    	int guessp1=0;
    	int guessp2=0;  // 3 guesses that player make
    	int guessp3=0;
    	
    	boolean p1isRight=false;
    	boolean p2isRight=false; // variable to hold a 'true' or 'false' based on the player answer
    	boolean p3isRight=false;
    	
    	// target number that player have to guess 
    	int targetNumber =(int) ( Math.random()*10);
    	System.out.println("I'm thinking of a number between 0 and 9...");
    	
    	while(true) {
    		
    		System.out.println("Number to guess is "+targetNumber);
    		p1.guess();
    		p2.guess(); // call each player guess method
    		p3.guess();
    		
    		guessp1=p1.number;
    		System.out.println("Player one guessed "+guessp1);
    		
    		guessp2=p2.number;
    		System.out.println("Player Two guessed "+guessp2);
    		
    		guessp3=p3.number;
    		System.out.println("Player Three guessed "+guessp3);
    		
    		if(guessp1==targetNumber) {
    			
    			p1isRight=true;
    		}
    		
             if(guessp2==targetNumber) {
    			
    			p2isRight=true;
    		}
             
             if(guessp3==targetNumber) {
     			
     			p3isRight=true;
     		}
             
            if(p1isRight || p2isRight || p3isRight) {
            	
            	System.out.println("We have a winner");
            	System.out.println("Player One got it Right? "+ p1isRight );
            	System.out.println("Player Two got it Right? "+ p2isRight );
            	System.out.println("Player Three got it Right? "+ p3isRight );
            	System.out.println("Game is Over");
            	break;
            }
            
            else {
            	// keep going because nobody is got right!
            	System.out.println("Players will have to try again");
            }
    		
    		
    	}
    	
    	
    }

}
