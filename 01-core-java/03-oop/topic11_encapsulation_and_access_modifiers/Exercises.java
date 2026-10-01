package topic11_encapsulation_and_access_modifiers;

/*
 * Exercises for topic 11.
 * How to use:
 *   - Complete the Temperature class written BELOW this one. Fill in every "TODO".
 *   - Then run:  java -cp out topic11_encapsulation_and_access_modifiers.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    public static void main(String[] args) {
        Temperature t = new Temperature(20);
        check(t.getCelsius() == 20, "exercise 1 getCelsius");
        check(t.getFahrenheit() == 68, "exercise 1 getFahrenheit");

        t.warmBy(5);
        check(t.getCelsius() == 25, "exercise 2 warmBy");

        try {
            new Temperature(-300);
            check(false, "exercise 3: -300 C is below absolute zero, the constructor must throw");
        } catch (IllegalArgumentException expected) {
            // it was refused - that is the correct behaviour
        }
        try {
            t.warmBy(-400);
            check(false, "exercise 3: warmBy must keep the rule too");
        } catch (IllegalArgumentException expected) {
            // refused
        }
        check(t.getCelsius() == 25, "exercise 3: a rejected change must leave the value as it was");

        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// A temperature that can NEVER go below absolute zero (-273.15 C), no matter how it is used.
// 1. Write the constructor, getCelsius() and getFahrenheit()   (F = C * 9 / 5 + 32)
// 2. warmBy(degrees): change the temperature by that many degrees
// 3. Both the constructor and warmBy must refuse a result below -273.15 with IllegalArgumentException.
//    Keep the field private, so no other code can break this rule.
class Temperature {
    // TODO: one private field

    Temperature(double celsius) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    double getCelsius() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    double getFahrenheit() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    void warmBy(double degrees) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }
}
