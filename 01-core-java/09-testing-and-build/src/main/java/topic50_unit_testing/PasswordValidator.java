package topic50_unit_testing;

/*
 * The class used in the topic 50 exercises (see src/test/java/topic50_unit_testing/ExercisesTest.java).
 * Password rules - like the ones on a net-banking sign-up page:
 *   - at least 8 characters
 *   - at least one digit
 *   - at least one capital letter
 *   - no spaces
 * This code is already finished. Your job is to PROVE it works, by writing tests.
 */
public class PasswordValidator {

    public boolean isValid(String password) {
        if (password == null) {
            throw new IllegalArgumentException("password must not be null");
        }
        return password.length() >= 8                                   // long enough
                && password.chars().anyMatch(Character::isDigit)        // has a digit
                && password.chars().anyMatch(Character::isUpperCase)    // has a capital letter
                && password.chars().noneMatch(Character::isWhitespace); // has no spaces
    }
}
