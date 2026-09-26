package topic02_classes_and_objects;

/*
 * Topic    : Classes and objects (Head First Java, chapter 2)
 * Key idea : main() only starts things; the objects (GuessGame, Player) do the work.
 * Run      : java -cp out topic02_classes_and_objects.GameLauncher
 * Read     : GameLauncher -> GuessGame -> Player
 */
public class GameLauncher {

    public static void main(String[] args) {
        GuessGame game = new GuessGame();
        game.startGame();
    }
}
