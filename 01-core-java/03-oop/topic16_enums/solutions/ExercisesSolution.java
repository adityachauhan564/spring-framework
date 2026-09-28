package topic16_enums.solutions;

// Solutions for topic16_enums/Exercises.java
public class ExercisesSolution {

    static int total(Coin... coins) {
        int sum = 0;
        for (Coin coin : coins) {
            sum += coin.paise();
        }
        return sum;
    }

    public static void main(String[] args) {
        check(TrafficLight.RED.next() == TrafficLight.GREEN, "exercise 1");
        check(TrafficLight.GREEN.next() == TrafficLight.YELLOW, "exercise 1");
        check(TrafficLight.YELLOW.next() == TrafficLight.RED, "exercise 1");

        check(Coin.ONE_RUPEE.paise() == 100 && Coin.FIFTY_PAISE.paise() == 50, "exercise 2");

        check(total(Coin.TWO_RUPEES, Coin.FIFTY_PAISE, Coin.FIFTY_PAISE) == 300, "exercise 3");
        check(total() == 0, "exercise 3 with no coins");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

enum TrafficLight {
    RED, GREEN, YELLOW;

    TrafficLight next() {
        return switch (this) {            // exhaustive: a new constant is a compile error until handled
            case RED -> GREEN;
            case GREEN -> YELLOW;
            case YELLOW -> RED;
        };
    }
}

enum Coin {
    FIFTY_PAISE(50), ONE_RUPEE(100), TWO_RUPEES(200);

    private final int paise;

    Coin(int paise) {                     // enum constructors are always private
        this.paise = paise;
    }

    int paise() {
        return paise;
    }
}
