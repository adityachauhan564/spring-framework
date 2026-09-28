package topic50_unit_testing;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/*
 * Exercises for topic 50: write the tests for PasswordValidator (src/main/java/topic50_unit_testing).
 * For each exercise: delete its @Disabled line, replace the fail(...) with real assertions, then run
 *   ./mvnw test -Dtest=ExercisesTest
 * A test you haven't written yet fails with "TODO". Stuck? See solutions/ExercisesSolutionTest.java.
 *
 * Tip: a good test fails when the code is wrong. Try breaking PasswordValidator on purpose
 * (change >= 8 to >= 7) and check that one of your tests goes red.
 */
class ExercisesTest {

    private final PasswordValidator validator = new PasswordValidator();

    // 1. A password that meets every rule is valid (for example "Secret123").
    @Disabled("exercise 1: remove this line and write the test")
    @Test
    void aStrongPasswordIsValid() {
        fail("TODO exercise 1");
    }

    // 2. Each rule on its own: too short, no digit, no upper-case letter, contains a space.
    //    One test per rule, or one @ParameterizedTest with a @ValueSource of bad passwords.
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

    // 4. null is rejected with an IllegalArgumentException (assertThrows).
    @Disabled("exercise 4: remove this line and write the test")
    @Test
    void nullIsRejected() {
        fail("TODO exercise 4");
    }
}
