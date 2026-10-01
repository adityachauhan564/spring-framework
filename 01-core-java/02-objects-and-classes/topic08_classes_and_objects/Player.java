package topic08_classes_and_objects;

/*
 * Every object has two things:
 *   - state     = what it KNOWS  (here: the number it guessed)
 *   - behaviour = what it DOES   (here: guess())
 * Each Player object has its own number, so three players can guess three different numbers.
 */
public class Player {

    private int number;                         // this player's latest guess

    public void guess() {
        number = (int) (Math.random() * 10);    // pick a random number from 0 to 9
    }

    public int getNumber() {
        return number;
    }
}
