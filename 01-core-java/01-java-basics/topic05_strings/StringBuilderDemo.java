package topic05_strings;

/*
 * Topic    : StringBuilder
 * Key idea : Using + inside a loop makes a brand-new String in every round, and throws
 *            the old one away. That is slow and wastes memory.
 *            StringBuilder keeps ONE piece of text and keeps changing it in place.
 *            Like writing on a whiteboard instead of using a new sheet of paper every time.
 *            Rule: building text inside a loop? Use StringBuilder.
 * Run      : java -cp out topic05_strings.StringBuilderDemo
 * Try this : Write isPalindrome(String) using reverse().
 */
public class StringBuilderDemo {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            sb.append(i);                             // append = add at the end
            if (i < 5) {
                sb.append(", ");
            }
        }
        System.out.println("Built:    " + sb);

        sb.insert(0, "[").append("]");                // insert at position 0 (the start), then add "]" at the end
        System.out.println("Insert:   " + sb);

        sb.reverse();                                 // flips the whole text back to front
        System.out.println("Reverse:  " + sb);

        sb.setLength(0);                              // empty it, so we can use the same builder again
        sb.append("Hello World").deleteCharAt(5).replace(0, 5, "Howdy");
        System.out.println("Edited:   " + sb);

        String result = sb.toString();                // turn it back into a normal (unchangeable) String
        System.out.println("toString: " + result);

        // A common interview question: reverse each word, but keep the word order
        System.out.println("Reverse words of 'I love Java': " + reverseWords("I love Java"));
    }

    // "I love Java" -> "I evol avaJ"
    static String reverseWords(String sentence) {
        StringBuilder out = new StringBuilder();
        for (String word : sentence.split(" ")) {
            out.append(new StringBuilder(word).reverse()).append(' ');
        }
        return out.toString().trim();                 // trim removes the extra space at the end
    }
}
