package topic35_optional.solutions;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// Answers for topic35_optional/Exercises.java
public class ExercisesSolution {

    record Address(String city) { }

    record User(String name, Address address) {
        Optional<Address> findAddress() {
            return Optional.ofNullable(address);
        }
    }

    static final Map<Integer, User> USERS = Map.of(
            1, new User("Asha", new Address("pune")),
            2, new User("Ravi", null));

    static Optional<User> findUser(int id) {
        return Optional.ofNullable(USERS.get(id));        // ofNullable: a null turns into an empty box
    }

    static String cityOf(int id) {
        return findUser(id)
                .flatMap(User::findAddress)                 // flatMap: findAddress already returns an Optional
                .map(Address::city)
                .map(String::toUpperCase)
                .orElse("UNKNOWN");                         // once the box is empty, every step above is skipped
    }

    static Optional<String> firstLongWord(List<String> words) {
        return words.stream().filter(word -> word.length() > 5).findFirst();   // findFirst already gives an Optional
    }

    static Optional<Integer> parse(String text) {
        try {
            return Optional.of(Integer.parseInt(text));     // it worked: a box with the number
        } catch (NumberFormatException e) {
            return Optional.empty();                        // not a number: an empty box, no crash
        }
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
