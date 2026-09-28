package topic05_strings.solutions;

// Solutions for topic05_strings/Exercises.java
public class ExercisesSolution {

    static boolean isPalindrome(String word) {
        String lower = word.toLowerCase();                  // assign the result: Strings never change in place
        return new StringBuilder(lower).reverse().toString().equals(lower);
    }

    static int countVowels(String text) {
        String lower = text.toLowerCase();
        int count = 0;
        for (int i = 0; i < lower.length(); i++) {
            if ("aeiou".indexOf(lower.charAt(i)) >= 0) {    // indexOf is -1 when the char isn't there
                count++;
            }
        }
        return count;
    }

    static String capitalizeWords(String sentence) {
        StringBuilder result = new StringBuilder();
        boolean startOfWord = true;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            result.append(startOfWord ? Character.toUpperCase(c) : c);
            startOfWord = c == ' ';                         // the next char starts a word after a space
        }
        return result.toString();
    }

    static String mask(String cardNumber) {
        int hidden = Math.max(0, cardNumber.length() - 4);
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

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
