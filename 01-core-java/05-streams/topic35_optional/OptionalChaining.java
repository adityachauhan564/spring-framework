package topic35_optional;

import java.util.Map;
import java.util.Optional;

/*
 * Topic    : Chaining Optionals with map, flatMap, filter and or
 * Key idea : An Optional has its own map / filter, just like a stream - think of it as a
 *            stream with 0 or 1 items. Each step runs ONLY if there is a value.
 *            If any step finds the box empty, all the following steps are simply skipped.
 *            So you don't need a pile of "if (x != null)" checks.
 * Run      : java -ea -cp out topic35_optional.OptionalChaining
 *            (-ea switches on the "assert" checks at the end)
 * Try this : Return the city in upper case, or "UNKNOWN".
 */
public class OptionalChaining {

    record Address(String city) { }

    record User(String name, Address address) {
        Optional<Address> findAddress() {            // not every user has filled in an address
            return Optional.ofNullable(address);
        }
    }

    private static final Map<Integer, User> USERS = Map.of(
            1, new User("Asha", new Address("Pune")),
            2, new User("Ravi", null));

    static Optional<User> findUser(int id) {
        return Optional.ofNullable(USERS.get(id));
    }

    // the old way with null checks - shown only for comparison
    static String cityOldStyle(int id) {
        User user = USERS.get(id);
        if (user != null && user.address() != null && user.address().city() != null) {
            return user.address().city();
        }
        return "unknown";
    }

    // the Optional way: if any step is empty, the rest are skipped and we land on orElse
    static String city(int id) {
        return findUser(id)
                .flatMap(User::findAddress)      // flatMap: because findAddress ALREADY returns an Optional
                .map(Address::city)              // map: city() returns a plain value, map wraps it for us
                .orElse("unknown");
    }

    public static void main(String[] args) {
        for (int id = 1; id <= 3; id++) {
            System.out.println("user " + id + ": city = " + city(id) + " (old style: " + cityOldStyle(id) + ")");
        }

        // filter: keep the value only if it passes the test, otherwise the box becomes empty
        Optional<String> longName = findUser(1).map(User::name).filter(name -> name.length() > 5);
        System.out.println("name longer than 5? " + longName);

        // or: if this box is empty, try another box instead (Java 9+)
        Optional<User> userOrGuest = findUser(99).or(() -> Optional.of(new User("Guest", null)));
        System.out.println("findUser(99).or(guest) -> " + userOrGuest.map(User::name).orElseThrow());

        // Optional.stream(): turns the box into a stream of 0 or 1 items - handy with flatMap over many ids
        long usersWithAddress = java.util.stream.Stream.of(1, 2, 3)
                .map(OptionalChaining::findUser)
                .flatMap(Optional::stream)                       // empty boxes simply disappear
                .filter(user -> user.findAddress().isPresent())
                .count();
        System.out.println("users with an address: " + usersWithAddress);

        assert city(1).equals("Pune") && city(2).equals("unknown") && city(3).equals("unknown");
        for (int id = 1; id <= 3; id++) {
            assert city(id).equals(cityOldStyle(id));
        }
    }
}
