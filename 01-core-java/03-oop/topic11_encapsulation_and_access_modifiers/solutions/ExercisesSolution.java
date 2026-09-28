package topic11_encapsulation_and_access_modifiers.solutions;

// Solutions for topic11_encapsulation_and_access_modifiers/Exercises.java
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
            // rejected, as it should be
        }
        try {
            t.warmBy(-400);
            check(false, "exercise 3: warmBy must keep the rule too");
        } catch (IllegalArgumentException expected) {
            // rejected
        }
        check(t.getCelsius() == 25, "exercise 3: a rejected change must leave the value as it was");

        System.out.println("All exercises pass");
    }

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

    double getFahrenheit() {              // a derived value: computed, not stored
        return celsius * 9 / 5 + 32;
    }

    void warmBy(double degrees) {
        celsius = validate(celsius + degrees);   // validate BEFORE changing the field
    }

    // one place for the rule, used by every way of changing the value
    private static double validate(double value) {
        if (value < ABSOLUTE_ZERO) {
            throw new IllegalArgumentException(value + " C is below absolute zero");
        }
        return value;
    }
}
