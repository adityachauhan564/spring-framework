package topic50_unit_testing.solutions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import topic50_unit_testing.PasswordValidator;

// Answers for topic50_unit_testing/ExercisesTest.java
class ExercisesSolutionTest {

    private final PasswordValidator validator = new PasswordValidator();

    @Test
    void aStrongPasswordIsValid() {
        assertTrue(validator.isValid("Secret123"));
    }

    // one test method, run once for each bad password below
    @ParameterizedTest(name = "\"{0}\" is invalid")
    @ValueSource(strings = {
            "Sec123",          // too short
            "SecretWord",      // no digit
            "secret123",       // no capital letter
            "Secret 123"       // has a space
    })
    void eachBrokenRuleMakesItInvalid(String password) {
        assertFalse(validator.isValid(password));
    }

    @Test
    void eightCharactersIsTheMinimum() {
        assertTrue(validator.isValid("Secret12"));       // 8 characters - just enough
        assertFalse(validator.isValid("Secre12"));       // 7 characters - one too few
    }

    @Test
    void nullIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> validator.isValid(null));
    }
}
