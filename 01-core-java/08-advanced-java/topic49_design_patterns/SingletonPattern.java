package topic49_design_patterns;

/*
 * Pattern  : Singleton - exactly ONE instance, shared by everyone
 * Use for  : shared configuration, a registry, a connection pool.
 * Key idea : a private constructor stops 'new'; the class hands out its one instance.
 *            An enum with one constant is the simplest thread-safe version.
 * Spring   : every bean is a singleton by default - the container does this for you, and you
 *            can still create extra instances in tests (a hand-written singleton makes that hard).
 * Run      : java -cp out topic49_design_patterns.SingletonPattern
 */
public class SingletonPattern {

    // classic version: private constructor + a static final instance, created when the class loads
    static final class AppConfig {
        private static final AppConfig INSTANCE = new AppConfig();
        private final String environment = "dev";

        private AppConfig() {
            System.out.println("  (AppConfig created - this line prints once)");
        }

        static AppConfig getInstance() {
            return INSTANCE;
        }

        String environment() {
            return environment;
        }
    }

    // enum version: the JVM guarantees one instance, even across threads and serialization
    enum IdGenerator {
        INSTANCE;

        private int next = 1;

        synchronized int nextId() {
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
