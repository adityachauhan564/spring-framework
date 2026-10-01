package topic05_strings.solutions;

// Answers for topic05_strings/Exercises.java
public class ExercisesSolution {

    static boolean isPalindrome(String word) {
        String lower = word.toLowerCase();                  // store the result - a String never changes by itself
        return new StringBuilder(lower).reverse().toString().equals(lower);   // reversed text == original text?
    }

    static int countVowels(String text) {
        String lower = text.toLowerCase();
        int count = 0;
        for (int i = 0; i < lower.length(); i++) {
            if ("aeiou".indexOf(lower.charAt(i)) >= 0) {    // indexOf gives -1 when the letter is not in "aeiou"
                count++;
            }
        }
        return count;
    }

    static String capitalizeWords(String sentence) {
        StringBuilder result = new StringBuilder();
        boolean startOfWord = true;                         // the very first letter starts a word
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            result.append(startOfWord ? Character.toUpperCase(c) : c);
            startOfWord = c == ' ';                         // after a space, the next letter starts a new word
        }
        return result.toString();
    }

    static String mask(String cardNumber) {
        int hidden = Math.max(0, cardNumber.length() - 4);  // how many digits to hide (never less than 0)
        return "*".repeat(hidden) + cardNumber.substring(hidden);
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
