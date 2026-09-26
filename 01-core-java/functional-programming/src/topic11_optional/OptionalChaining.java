package topic11_optional;

import java.util.Map;
import java.util.Optional;

/*
 * Topic    : Chaining Optionals with map, flatMap, filter and or
 * Key idea : an Optional has its own map/filter, like a stream of 0 or 1 elements.
 *            Each step runs only if a value is present - no if-null checks needed.
 * Run      : java -ea -cp out topic11_optional.OptionalChaining
 * Try this : return the city in upper case, or "UNKNOWN".
 */
public class OptionalChaining {

    record Address(String city) { }

    record User(String name, Address address) {
        Optional<Address> findAddress() {            // the address might not be set
            return Optional.ofNullable(address);
        }
    }

    private static final Map<Integer, User> USERS = Map.of(
            1, new User("Asha", new Address("Pune")),
            2, new User("Ravi", null));

    static Optional<User> findUser(int id) {
        return Optional.ofNullable(USERS.get(id));
    }

    // null-check version, for comparison
    static String cityOldStyle(int id) {
        User user = USERS.get(id);
        if (user != null && user.address() != null && user.address().city() != null) {
            return user.address().city();
        }
        return "unknown";
    }

    // Optional version: each step is skipped if an earlier one was empty
    static String city(int id) {
        return findUser(id)
                .flatMap(User::findAddress)      // flatMap: the method already returns an Optional
                .map(Address::city)              // map: plain value -> wrapped for us
                .orElse("unknown");
    }

    public static void main(String[] args) {
        for (int id = 1; id <= 3; id++) {
            System.out.println("user " + id + ": city = " + city(id) + " (old style: " + cityOldStyle(id) + ")");
        }

        // filter: keep the value only if it matches
        Optional<String> longName = findUser(1).map(User::name).filter(name -> name.length() > 5);
        System.out.println("name longer than 5? " + longName);

        // or: fall back to another Optional (Java 9+)
        Optional<User> userOrGuest = findUser(99).or(() -> Optional.of(new User("Guest", null)));
        System.out.println("findUser(99).or(guest) -> " + userOrGuest.map(User::name).orElseThrow());

        // Optional.stream(): 0 or 1 elements, handy inside flatMap over many ids
        long usersWithAddress = java.util.stream.Stream.of(1, 2, 3)
                .map(OptionalChaining::findUser)
                .flatMap(Optional::stream)
                .filter(user -> user.findAddress().isPresent())
                .count();
        System.out.println("users with an address: " + usersWithAddress);

        assert city(1).equals("Pune") && city(2).equals("unknown") && city(3).equals("unknown");
        for (int id = 1; id <= 3; id++) {
            assert city(id).equals(cityOldStyle(id));
        }
    }
}
