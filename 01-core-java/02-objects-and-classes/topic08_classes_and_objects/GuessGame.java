package topic08_classes_and_objects;

/*
 * GuessGame has three Player objects inside it (stored as instance variables).
 * In every round it asks each player to guess a number from 0 to 9,
 * and keeps going until at least one player guesses right.
 */
public class GuessGame {

    // three separate Player objects - each one remembers its own guess
    private final Player p1 = new Player();
    private final Player p2 = new Player();
    private final Player p3 = new Player();

    public void startGame() {
        // Math.random() gives a decimal from 0.0 up to (not including) 1.0.
        // Multiply by 10 and cut off the decimals -> a whole number from 0 to 9
        int targetNumber = (int) (Math.random() * 10);
        System.out.println("I'm thinking of a number between 0 and 9...");

        int round = 1;
        while (true) {                          // loop forever - the "break" below is the only way out
            System.out.println("\nRound " + round + " (number to guess is " + targetNumber + ")");

            // same method, three different objects -> three different guesses
            p1.guess();
            p2.guess();
            p3.guess();

            System.out.println("Player one guessed   " + p1.getNumber());
            System.out.println("Player two guessed   " + p2.getNumber());
            System.out.println("Player three guessed " + p3.getNumber());

            boolean p1IsRight = p1.getNumber() == targetNumber;
            boolean p2IsRight = p2.getNumber() == targetNumber;
            boolean p3IsRight = p3.getNumber() == targetNumber;

            if (p1IsRight || p2IsRight || p3IsRight) {      // did anyone get it right?
                System.out.println("\nWe have a winner!");
                System.out.println("Player one got it right?   " + p1IsRight);
                System.out.println("Player two got it right?   " + p2IsRight);
                System.out.println("Player three got it right? " + p3IsRight);
                System.out.println("Game is over.");
                break;                                      // stop the loop, game finished
            }
            System.out.println("Players will have to try again.");
            round++;
        }
    }
}
