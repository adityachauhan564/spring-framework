package topic16_enums;

/*
 * Exercises for topic 16.
 * How to use:
 *   - Complete the enums written BELOW this class, and the total method. Fill in every "TODO".
 *   - Then run:  java -cp out topic16_enums.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 3. Return the total value, in paise, of a handful of coins.
    static int total(Coin... coins) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) {
        // 1. A traffic light goes round: RED -> GREEN -> YELLOW -> RED
        check(TrafficLight.RED.next() == TrafficLight.GREEN, "exercise 1");
        check(TrafficLight.GREEN.next() == TrafficLight.YELLOW, "exercise 1");
        check(TrafficLight.YELLOW.next() == TrafficLight.RED, "exercise 1");

        // 2. Each coin knows its own value in paise (100 paise = 1 rupee)
        check(Coin.ONE_RUPEE.paise() == 100 && Coin.FIFTY_PAISE.paise() == 50, "exercise 2");

        check(total(Coin.TWO_RUPEES, Coin.FIFTY_PAISE, Coin.FIFTY_PAISE) == 300, "exercise 3");
        check(total() == 0, "exercise 3 with no coins");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 1. Write next() using a switch expression.
//    That way, if someone adds a new colour later, the compiler forces them to handle it.
enum TrafficLight {
    RED, GREEN, YELLOW;

    TrafficLight next() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }
}

// 2. Give each coin its value: FIFTY_PAISE = 50, ONE_RUPEE = 100, TWO_RUPEES = 200.
//    Hint: you need a private final field, a constructor, and the value in brackets after each constant.
enum Coin {
    FIFTY_PAISE, ONE_RUPEE, TWO_RUPEES;

    int paise() {
        throw new UnsupportedOperationException("TODO exercise 2");
    }
}
