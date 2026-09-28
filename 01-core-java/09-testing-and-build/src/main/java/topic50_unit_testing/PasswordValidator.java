package topic50_unit_testing;

/*
 * The class for the topic 50 exercises (see src/test/java/topic50_unit_testing/ExercisesTest.java).
 * Rules: at least 8 characters, at least one digit, at least one upper-case letter, and no spaces.
 * The code is finished - your job is to prove it with tests.
 */
public class PasswordValidator {

    public boolean isValid(String password) {
        if (password == null) {
            throw new IllegalArgumentException("password must not be null");
        }
        return password.length() >= 8
                && password.chars().anyMatch(Character::isDigit)
                && password.chars().anyMatch(Character::isUpperCase)
                && password.chars().noneMatch(Character::isWhitespace);
    }
}
