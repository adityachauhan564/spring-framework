package topic51_maven;

/*
 * Topic    : A program packed up by Maven
 * Key idea : ./mvnw package does three things, in order:
 *              1. compiles everything in src/main/java
 *              2. runs EVERY test
 *              3. only if all tests pass - packs the classes into target/testing-and-build-1.0.0.jar
 *            The pom.xml tells the jar which class has main(), so the jar can run on its own.
 *            Like a sweet shop: the sweets are made, quality-checked, and only then boxed and sealed.
 *            A failed check means no box.
 * Run      : ./mvnw package
 *            java -jar target/testing-and-build-1.0.0.jar
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello from a jar built by Maven");
        // prints the Java version, and where this class was loaded from (the jar file)
        System.out.println("Java " + System.getProperty("java.version") + ", started from: "
                + App.class.getProtectionDomain().getCodeSource().getLocation());
    }
}
