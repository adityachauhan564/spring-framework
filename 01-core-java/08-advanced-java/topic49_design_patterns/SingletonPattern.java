package topic49_design_patterns;

/*
 * Pattern  : Singleton - exactly ONE object of a class, shared by everyone
 * Use for  : shared settings, a registry, a database connection pool.
 *            Like a country having only one Prime Minister at a time - everyone talks to the same one.
 * Key idea : A private constructor stops anyone outside from writing 'new'.
 *            The class makes its one object itself and hands out that same object every time.
 *            An enum with one constant is the simplest version that is also thread-safe.
 * Spring   : Every Spring bean is a singleton by default - Spring does this for you.
 *            And you can still make extra objects in tests (a hand-written singleton makes that hard).
 * Run      : java -cp out topic49_design_patterns.SingletonPattern
 */
public class SingletonPattern {

    // classic version: private constructor + one static final object, made when the class is loaded
    static final class AppConfig {
        private static final AppConfig INSTANCE = new AppConfig();      // the one and only object
        private final String environment = "dev";

        private AppConfig() {                                            // private: nobody outside can call 'new'
            System.out.println("  (AppConfig created - this line prints once)");
        }

        static AppConfig getInstance() {                                 // everyone gets the SAME object
            return INSTANCE;
        }

        String environment() {
            return environment;
        }
    }

    // enum version: Java itself guarantees only one object - even with many threads, even with serialization
    enum IdGenerator {
        INSTANCE;

        private int next = 1;

        synchronized int nextId() {           // synchronized: two threads can never get the same id
            return next++;
        }
    }

    public static void main(String[] args) {
        AppConfig a = AppConfig.getInstance();
        AppConfig b = AppConfig.getInstance();
        System.out.println("same object? " + (a == b) + ", environment " + a.environment());

        System.out.println("ids: " + IdGenerator.INSTANCE.nextId() + ", " + IdGenerator.INSTANCE.nextId());
        // new AppConfig();   // compile error: the constructor is private
    }
}
