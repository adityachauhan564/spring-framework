package topic50_unit_testing;

/*
 * Topic    : The code we are going to test (from in28minutes "JUnit in 5 steps")
 * Key idea : - Real (production) code lives in src/main/java.
 *            - Its tests live in src/test/java, in the SAME package name,
 *              so the tests can also see members that have no access keyword (package-private).
 *            - Maven compiles both and runs the tests for you.
 *            Like a car factory: one line builds the car (main), another line test-drives it (test).
 * Test     : ./mvnw test -Dtest=MyMathsTest
 */
public class MyMaths {

    // adds up every number: {2, 3, 5, 6} -> 2 + 3 + 5 + 6 = 16
    public int sum(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}
