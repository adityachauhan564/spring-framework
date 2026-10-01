package topic18_custom_exceptions;

/*
 * Exercises for topic 18.
 * How to use:
 *   - Complete the code below. Fill in every "TODO".
 *   - Then run:  java -cp out topic18_custom_exceptions.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 2. Accept ages from 0 to 150. For anything else, throw an InvalidAgeException that carries the bad age.
    static int validateAge(int age) throws InvalidAgeException {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Read a port number from text. If the text is not a number, throw a ConfigException
    //    with the message "bad port: <text>", AND pass the NumberFormatException along as its cause.
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

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 1. A CHECKED exception (it extends Exception).
//    Its message must be "invalid age: <age>", and it needs a getAge() getter.
class InvalidAgeException extends Exception {
    InvalidAgeException(int age) {
        // TODO: call super(...) with the message, and keep the age
    }

    int getAge() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }
}

// 3. An UNCHECKED exception that can also carry a "cause" (the original exception). Complete the constructor.
class ConfigException extends RuntimeException {
    ConfigException(String message, Throwable cause) {
        // TODO: pass both to super
    }
}
