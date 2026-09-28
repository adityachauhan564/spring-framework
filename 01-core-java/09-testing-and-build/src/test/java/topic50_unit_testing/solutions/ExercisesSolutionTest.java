package topic50_unit_testing.solutions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import topic50_unit_testing.PasswordValidator;

// Solutions for topic50_unit_testing/ExercisesTest.java
class ExercisesSolutionTest {

    private final PasswordValidator validator = new PasswordValidator();

    @Test
    void aStrongPasswordIsValid() {
        assertTrue(validator.isValid("Secret123"));
    }

    @ParameterizedTest(name = "\"{0}\" is invalid")
    @ValueSource(strings = {
            "Sec123",          // too short
            "SecretWord",      // no digit
            "secret123",       // no upper-case letter
            "Secret 123"       // a space
    })
    void eachBrokenRuleMakesItInvalid(String password) {
        assertFalse(validator.isValid(password));
    }

    @Test
    void eightCharactersIsTheMinimum() {
        assertTrue(validator.isValid("Secret12"));       // 8
        assertFalse(validator.isValid("Secre12"));       // 7
    }

    @Test
    void nullIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> validator.isValid(null));
    }
}
