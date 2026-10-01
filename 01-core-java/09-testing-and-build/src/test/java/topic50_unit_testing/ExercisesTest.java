package topic50_unit_testing;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/*
 * Exercises for topic 50: write the tests for PasswordValidator (in src/main/java/topic50_unit_testing).
 * How to use:
 *   - For each exercise, delete its @Disabled line, and replace fail(...) with real assertions.
 *   - Then run:  ./mvnw test -Dtest=ExercisesTest
 *   - A test you haven't written yet fails with "TODO".
 * Stuck? See solutions/ExercisesSolutionTest.java.
 *
 * Tip: a good test FAILS when the code is wrong. Try breaking PasswordValidator on purpose
 * (change >= 8 to >= 7) and check that at least one of your tests turns red.
 */
class ExercisesTest {

    private final PasswordValidator validator = new PasswordValidator();

    // 1. A password that follows every rule is valid (for example "Secret123").
    @Disabled("exercise 1: remove this line and write the test")
    @Test
    void aStrongPasswordIsValid() {
        fail("TODO exercise 1");
    }

    // 2. Break each rule on its own: too short, no digit, no capital letter, has a space.
    //    Write one test per rule - or one @ParameterizedTest with a @ValueSource of bad passwords.
    @Disabled("exercise 2: remove this line and write the test")
    @Test
    void eachBrokenRuleMakesItInvalid() {
        fail("TODO exercise 2");
    }

    // 3. The boundary: exactly 8 characters is fine, 7 is not.
    @Disabled("exercise 3: remove this line and write the test")
    @Test
    void eightCharactersIsTheMinimum() {
        fail("TODO exercise 3");
    }

    // 4. null must be refused with an IllegalArgumentException (use assertThrows).
    @Disabled("exercise 4: remove this line and write the test")
    @Test
    void nullIsRejected() {
        fail("TODO exercise 4");
    }
}
