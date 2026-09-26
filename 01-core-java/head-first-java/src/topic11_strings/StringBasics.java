package topic11_strings;

/*
 * Topic    : Strings
 * Key idea : a String is immutable - methods like toUpperCase() return a NEW string.
 *            Compare text with equals(), never with ==.
 * Run      : java -cp out topic11_strings.StringBasics
 * Next     : StringBuilderDemo
 */
public class StringBasics {

    public static void main(String[] args) {
        String text = "  Hello, Java World  ";

        // common methods
        System.out.println("length()        : " + text.length());
        System.out.println("trim()          : '" + text.trim() + "'");
        System.out.println("toUpperCase()   : " + text.trim().toUpperCase());
        System.out.println("charAt(2)       : " + text.charAt(2));
        System.out.println("indexOf(\"Java\") : " + text.indexOf("Java"));
        System.out.println("substring(9,13) : " + text.substring(9, 13));        // end index is excluded
        System.out.println("replace         : " + text.trim().replace("Java", "Kotlin"));
        System.out.println("contains(\"World\"): " + text.contains("World"));
        System.out.println("isBlank()       : " + "   ".isBlank());

        // split and join
        String csv = "red,green,blue";
        String[] colors = csv.split(",");
        System.out.println("split -> " + colors.length + " parts, join -> " + String.join(" | ", colors));

        // immutability: the original is unchanged unless you reassign
        String name = "aditya";
        name.toUpperCase();                       // result thrown away
        System.out.println("After toUpperCase() without assigning: " + name);
        name = name.toUpperCase();
        System.out.println("After name = name.toUpperCase():       " + name);

        // == vs equals
        String a = "java";                        // literal: stored once in the String pool
        String b = "java";                        // same pooled object
        String c = new String("java");            // forced new object
        System.out.println("a == b      : " + (a == b));
        System.out.println("a == c      : " + (a == c) + "   <- different objects");
        System.out.println("a.equals(c) : " + a.equals(c) + "    <- same text: use this");
        System.out.println("equalsIgnoreCase: " + "JAVA".equalsIgnoreCase(a));

        // null-safe comparison: put the literal first
        String input = null;
        System.out.println("\"yes\".equals(null) : " + "yes".equals(input));   // no NullPointerException

        // formatting
        System.out.println(String.format("%-8s scored %5.1f%%", "Aditya", 92.456));
    }
}
