package topic05_strings;

/*
 * Exercises for topic 05. Replace each "TODO" line with your code, then run:
 *   java -cp out topic05_strings.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. true if the word reads the same backwards, ignoring upper/lower case: "Level" -> true.
    static boolean isPalindrome(String word) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. How many vowels (a, e, i, o, u, either case) are in the text?
    static int countVowels(String text) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Capitalise the first letter of every word: "hello java world" -> "Hello Java World".
    //    Words are separated by single spaces. Build the result with a StringBuilder.
    static String capitalizeWords(String sentence) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Mask all but the last 4 characters of a card number: "1234567812345678" -> "************5678".
    static String mask(String cardNumber) {
        throw new UnsupportedOperationException("TODO exercise 4");
    }

    public static void main(String[] args) {
        check(isPalindrome("Level") && isPalindrome("noon") && !isPalindrome("java"), "exercise 1");
        check(countVowels("Hello World") == 3 && countVowels("xyz") == 0, "exercise 2");
        check(capitalizeWords("hello java world").equals("Hello Java World"), "exercise 3");
        check(mask("1234567812345678").equals("************5678"), "exercise 4");
        check(mask("1234").equals("1234"), "exercise 4");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
