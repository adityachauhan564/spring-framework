package topic02_classes_and_objects;

/*
 * A Player is an object with state (the number it guessed)
 * and behaviour (guess()). Every Player object has its own number.
 */
public class Player {

    private int number;

    public void guess() {
        number = (int) (Math.random() * 10);
    }

    public int getNumber() {
        return number;
    }
}
