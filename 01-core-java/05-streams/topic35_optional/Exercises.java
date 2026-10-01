package topic35_optional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
 * Exercises for topic 35.
 * How to use:
 *   - Replace each "TODO" with code that uses Optional.
 *     No "if (x == null)" checks, and no bare get().
 *   - Then run:  java -cp out topic35_optional.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    record Address(String city) { }

    record User(String name, Address address) {
        Optional<Address> findAddress() {
            return Optional.ofNullable(address);
        }
    }

    static final Map<Integer, User> USERS = Map.of(
            1, new User("Asha", new Address("pune")),
            2, new User("Ravi", null));

    // 1. Return the user with this id, as an Optional. (USERS.get gives null when the id is not there.)
    static Optional<User> findUser(int id) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. The user's city in CAPITAL letters, or "UNKNOWN" if the user or the address is missing.
    //    Write it as one chain: findUser -> flatMap -> map -> map -> orElse
    static String cityOf(int id) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. The first word longer than 5 letters, as an Optional.
    static Optional<String> firstLongWord(List<String> words) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Turn the text into an int. Return an empty Optional if the text is not a number.
    static Optional<Integer> parse(String text) {
        throw new UnsupportedOperationException("TODO exercise 4");
    }

    public static void main(String[] args) {
        check(findUser(1).isPresent() && findUser(9).isEmpty(), "exercise 1");
        check(cityOf(1).equals("PUNE") && cityOf(2).equals("UNKNOWN") && cityOf(9).equals("UNKNOWN"), "exercise 2");
        check(firstLongWord(List.of("java", "streams", "optional")).orElse("").equals("streams"), "exercise 3");
        check(firstLongWord(List.of("a", "b")).isEmpty(), "exercise 3");
        check(parse("42").orElse(0) == 42 && parse("x").isEmpty(), "exercise 4");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
