package topic50_unit_testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/*
 * Topic    : Your first JUnit 5 test
 * Key idea : - A test method has @Test on top, and it must ASSERT (check) the result.
 *              Just printing the result proves nothing - nobody reads the output of a build.
 *            - Give each test a name that says what behaviour it checks.
 *            - Every test has 3 parts - Arrange, Act, Assert:
 *                Arrange = prepare the input
 *                Act     = call the method
 *                Assert  = check the answer
 *              Like a teacher checking a sum: set the question, let the student solve it, tick or cross.
 * Run      : ./mvnw test -Dtest=MyMathsTest
 */
class MyMathsTest {

    private final MyMaths maths = new MyMaths();       // JUnit makes a brand-new test object for EVERY test, so tests never affect each other

    @Test
    void sumAddsAllNumbers() {
        int[] numbers = {1, 2, 3};                       // arrange

        int result = maths.sum(numbers);                 // act

        assertEquals(6, result);                         // assert: the EXPECTED value first, then the actual one
    }

    @Test
    void sumOfNoNumbersIsZero() {
        assertEquals(0, maths.sum(new int[] {}));        // the edge case: an empty array
    }

    @Test
    void sumHandlesNegativeNumbers() {
        // the third argument is the message shown if this test fails
        assertEquals(-2, maths.sum(new int[] {-5, 3}), "negative and positive numbers cancel out");
    }
}
