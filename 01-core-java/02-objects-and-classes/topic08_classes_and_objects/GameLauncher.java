package topic08_classes_and_objects;

/*
 * Topic    : Classes and objects (from Head First Java, chapter 2)
 * Key idea : main() should only START the program. The real work is done by objects.
 *            Like a cricket match: the umpire (main) only says "play", the players do the work.
 *            Here GuessGame runs the game and each Player makes guesses.
 * Run      : java -cp out topic08_classes_and_objects.GameLauncher
 * Read     : in this order: GameLauncher -> GuessGame -> Player
 */
public class GameLauncher {

    public static void main(String[] args) {
        GuessGame game = new GuessGame();       // create the game object
        game.startGame();                       // and tell it to start
    }
}
