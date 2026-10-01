package topic11_encapsulation_and_access_modifiers.solutions;

// Answers for topic11_encapsulation_and_access_modifiers/Exercises.java
public class ExercisesSolution {

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

class Temperature {
    private static final double ABSOLUTE_ZERO = -273.15;

    private double celsius;               // private: only this class can change it

    Temperature(double celsius) {
        this.celsius = validate(celsius);
    }

    double getCelsius() {
        return celsius;
    }

    double getFahrenheit() {              // not stored anywhere - worked out from celsius every time it is asked
        return celsius * 9 / 5 + 32;
    }

    void warmBy(double degrees) {
        celsius = validate(celsius + degrees);   // check FIRST, change the field only if the check passes
    }

    // the rule lives in ONE place, and every method that changes the value uses it
    private static double validate(double value) {
        if (value < ABSOLUTE_ZERO) {
            throw new IllegalArgumentException(value + " C is below absolute zero");
        }
        return value;
    }
}
