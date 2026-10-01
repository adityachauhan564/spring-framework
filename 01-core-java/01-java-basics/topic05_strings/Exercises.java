package topic05_strings;

/*
 * Exercises for topic 05.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic05_strings.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Return true if the word reads the same from both sides, ignoring capital/small letters.
    //    "Level" -> true (like the name "Nitin").
    static boolean isPalindrome(String word) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. How many vowels (a, e, i, o, u - capital or small) are there in the text?
    static int countVowels(String text) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Make the first letter of every word capital: "hello java world" -> "Hello Java World".
    //    Words have one space between them. Build the answer with a StringBuilder.
    static String capitalizeWords(String sentence) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Hide a card number except the last 4 digits, like your bank SMS does:
    //    "1234567812345678" -> "************5678"
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

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
