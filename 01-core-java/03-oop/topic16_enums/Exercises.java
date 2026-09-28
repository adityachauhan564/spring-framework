package topic16_enums;

/*
 * Exercises for topic 16. Complete the enums below this class, then run:
 *   java -cp out topic16_enums.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 3. The total value in paise of a handful of coins.
    static int total(Coin... coins) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) {
        // 1. A traffic light cycles RED -> GREEN -> YELLOW -> RED
        check(TrafficLight.RED.next() == TrafficLight.GREEN, "exercise 1");
        check(TrafficLight.GREEN.next() == TrafficLight.YELLOW, "exercise 1");
        check(TrafficLight.YELLOW.next() == TrafficLight.RED, "exercise 1");

        // 2. Each coin knows its value in paise (100 paise = 1 rupee)
        check(Coin.ONE_RUPEE.paise() == 100 && Coin.FIFTY_PAISE.paise() == 50, "exercise 2");

        check(total(Coin.TWO_RUPEES, Coin.FIFTY_PAISE, Coin.FIFTY_PAISE) == 300, "exercise 3");
        check(total() == 0, "exercise 3 with no coins");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 1. next() with a switch expression - so adding a colour later forces you to handle it
enum TrafficLight {
    RED, GREEN, YELLOW;

    TrafficLight next() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }
}

// 2. Give each constant its value: FIFTY_PAISE = 50, ONE_RUPEE = 100, TWO_RUPEES = 200.
//    Hint: a private final field, a constructor, and the values in brackets after each constant.
enum Coin {
    FIFTY_PAISE, ONE_RUPEE, TWO_RUPEES;

    int paise() {
        throw new UnsupportedOperationException("TODO exercise 2");
    }
}
