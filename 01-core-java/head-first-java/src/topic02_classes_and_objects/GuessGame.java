package topic02_classes_and_objects;

/*
 * GuessGame owns three Player objects (instance variables)
 * and asks each of them to guess until someone hits the target.
 */
public class GuessGame {

    private final Player p1 = new Player();
    private final Player p2 = new Player();
    private final Player p3 = new Player();

    public void startGame() {
        int targetNumber = (int) (Math.random() * 10);
        System.out.println("I'm thinking of a number between 0 and 9...");

        int round = 1;
        while (true) {
            System.out.println("\nRound " + round + " (number to guess is " + targetNumber + ")");

            p1.guess();
            p2.guess();
            p3.guess();

            System.out.println("Player one guessed   " + p1.getNumber());
            System.out.println("Player two guessed   " + p2.getNumber());
            System.out.println("Player three guessed " + p3.getNumber());

            boolean p1IsRight = p1.getNumber() == targetNumber;
            boolean p2IsRight = p2.getNumber() == targetNumber;
            boolean p3IsRight = p3.getNumber() == targetNumber;

            if (p1IsRight || p2IsRight || p3IsRight) {
                System.out.println("\nWe have a winner!");
                System.out.println("Player one got it right?   " + p1IsRight);
                System.out.println("Player two got it right?   " + p2IsRight);
                System.out.println("Player three got it right? " + p3IsRight);
                System.out.println("Game is over.");
                break;
            }
            System.out.println("Players will have to try again.");
            round++;
        }
    }
}
