package topic50_unit_testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/*
 * Topic    : A first JUnit 5 test
 * Key idea : a test method is annotated @Test and ASSERTS the result - printing proves nothing,
 *            because nobody reads the output of a build. Name tests after the behaviour they check.
 *            Arrange (build input) -> Act (call the method) -> Assert (check the result).
 * Run      : ./mvnw test -Dtest=MyMathsTest
 */
class MyMathsTest {

    private final MyMaths maths = new MyMaths();       // JUnit creates a new test object for EVERY test

    @Test
    void sumAddsAllNumbers() {
        int[] numbers = {1, 2, 3};                       // arrange

        int result = maths.sum(numbers);                 // act

        assertEquals(6, result);                         // assert: expected FIRST, then actual
    }

    @Test
    void sumOfNoNumbersIsZero() {
        assertEquals(0, maths.sum(new int[] {}));
    }

    @Test
    void sumHandlesNegativeNumbers() {
        assertEquals(-2, maths.sum(new int[] {-5, 3}), "negative and positive numbers cancel out");
    }
}
