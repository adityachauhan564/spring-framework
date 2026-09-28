package topic18_custom_exceptions;

/*
 * Exercises for topic 18. Complete the code below, then run:
 *   java -cp out topic18_custom_exceptions.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 2. Accept ages 0-150; otherwise throw InvalidAgeException carrying the bad age.
    static int validateAge(int age) throws InvalidAgeException {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Read a port number from text. If the text isn't a number, throw a ConfigException
    //    with the message "bad port: <text>" AND the NumberFormatException as its cause.
    static int parsePort(String text) {
        throw new UnsupportedOperationException("TODO exercise 3");
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

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 1. A CHECKED exception (extends Exception) with the message "invalid age: <age>" and a getAge() getter.
class InvalidAgeException extends Exception {
    InvalidAgeException(int age) {
        // TODO: call super(...) with the message, and keep the age
    }

    int getAge() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }
}

// 3. An UNCHECKED exception that can carry a cause: complete the constructor.
class ConfigException extends RuntimeException {
    ConfigException(String message, Throwable cause) {
        // TODO: pass both to super
    }
}
