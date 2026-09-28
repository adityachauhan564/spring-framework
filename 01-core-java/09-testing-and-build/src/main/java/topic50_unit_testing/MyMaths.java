package topic50_unit_testing;

/*
 * Topic    : The code under test (in28minutes "JUnit in 5 steps")
 * Key idea : production code lives in src/main/java; its tests live in src/test/java, in the
 *            SAME package, so they can see package-private members. Maven compiles and runs both.
 * Test     : ./mvnw test -Dtest=MyMathsTest
 */
public class MyMaths {

    // {2, 3, 5, 6} -> 2 + 3 + 5 + 6
    public int sum(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}
