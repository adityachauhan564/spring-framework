package topic51_maven;

/*
 * Topic    : A program packaged by Maven
 * Key idea : ./mvnw package compiles src/main/java, runs every test, and - only if they all pass -
 *            puts the classes into target/testing-and-build-1.0.0.jar. The pom tells the jar which
 *            class has main, so the jar runs on its own.
 * Run      : ./mvnw package
 *            java -jar target/testing-and-build-1.0.0.jar
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello from a jar built by Maven");
        System.out.println("Java " + System.getProperty("java.version") + ", started from: "
                + App.class.getProtectionDomain().getCodeSource().getLocation());
    }
}
