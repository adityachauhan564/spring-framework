package topic18_custom_exceptions.solutions;

// Answers for topic18_custom_exceptions/Exercises.java
public class ExercisesSolution {

    static int validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException(age);
        }
        return age;
    }

    static int parsePort(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            // Change the low-level error into one that makes sense here ("bad port"),
            // but KEEP the original as the cause. Then the error report shows both -
            // exactly what you need when you are hunting a bug.
            throw new ConfigException("bad port: " + text, e);
        }
    }

    public static void main(String[] args) {
        try {
            check(validateAge(30) == 30, "exercise 2 a valid age is returned");
            validateAge(200);
            check(false, "exercise 2 must throw for 200");
        } catch (InvalidAgeException e) {
            check(e.getAge() == 200, "exercise 1/2 the exception carries the age");
            check(e.getMessage().equals("invalid age: 200"), "exercise 1 message");
        }

        check(parsePort("8080") == 8080, "exercise 3 a valid port");
        try {
            parsePort("eighty");
            check(false, "exercise 3 must throw for 'eighty'");
        } catch (ConfigException e) {
            check(e.getMessage().equals("bad port: eighty"), "exercise 3 message");
            check(e.getCause() instanceof NumberFormatException, "exercise 3 keep the original cause");
        }
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

class InvalidAgeException extends Exception {
    private final int age;

    InvalidAgeException(int age) {
        super("invalid age: " + age);          // this text becomes getMessage()
        this.age = age;
    }

    int getAge() {
        return age;
    }
}

class ConfigException extends RuntimeException {
    ConfigException(String message, Throwable cause) {
        super(message, cause);                 // these become getMessage() and getCause()
    }
}
