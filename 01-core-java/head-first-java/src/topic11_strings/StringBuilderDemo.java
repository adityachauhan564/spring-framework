package topic11_strings;

/*
 * Topic    : StringBuilder
 * Key idea : '+' in a loop creates a new String every pass. StringBuilder changes
 *            one buffer in place, so use it when building text in a loop.
 * Run      : java -cp out topic11_strings.StringBuilderDemo
 * Try this : write isPalindrome(String) using reverse().
 */
public class StringBuilderDemo {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            sb.append(i);
            if (i < 5) {
                sb.append(", ");
            }
        }
        System.out.println("Built:    " + sb);

        sb.insert(0, "[").append("]");
        System.out.println("Insert:   " + sb);

        sb.reverse();
        System.out.println("Reverse:  " + sb);

        sb.setLength(0);                              // reuse the same builder
        sb.append("Hello World").deleteCharAt(5).replace(0, 5, "Howdy");
        System.out.println("Edited:   " + sb);

        String result = sb.toString();                // back to an immutable String
        System.out.println("toString: " + result);

        // Classic interview question: reverse each word
        System.out.println("Reverse words of 'I love Java': " + reverseWords("I love Java"));
    }

    static String reverseWords(String sentence) {
        StringBuilder out = new StringBuilder();
        for (String word : sentence.split(" ")) {
            out.append(new StringBuilder(word).reverse()).append(' ');
        }
        return out.toString().trim();
    }
}
