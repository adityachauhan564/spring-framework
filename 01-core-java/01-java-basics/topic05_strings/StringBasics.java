package topic05_strings;

/*
 * Topic    : Strings
 * Key idea : - A String can never be changed once it is made (it is "immutable").
 *              Methods like toUpperCase() do NOT change the old string - they give you
 *              a NEW string. If you don't store that new string, it is lost.
 *              Like a printed bus ticket: you can't edit it, you get a new one printed.
 *            - To compare text, always use equals(). Never use ==.
 * Run      : java -cp out topic05_strings.StringBasics
 * Next     : StringBuilderDemo
 */
public class StringBasics {

    public static void main(String[] args) {
        String text = "  Hello, Java World  ";

        // methods you will use daily
        System.out.println("length()        : " + text.length());                   // number of characters, spaces also counted
        System.out.println("trim()          : '" + text.trim() + "'");              // removes spaces at the start and end
        System.out.println("toUpperCase()   : " + text.trim().toUpperCase());
        System.out.println("charAt(2)       : " + text.charAt(2));                  // character at position 2 (counting starts from 0)
        System.out.println("indexOf(\"Java\") : " + text.indexOf("Java"));          // where does "Java" start? -1 if not found
        System.out.println("substring(9,13) : " + text.substring(9, 13));        // from 9 up to 13 - the end position 13 is NOT included
        System.out.println("replace         : " + text.trim().replace("Java", "Kotlin"));
        System.out.println("contains(\"World\"): " + text.contains("World"));
        System.out.println("isBlank()       : " + "   ".isBlank());                 // true if empty or only spaces

        // split: cut one string into pieces. join: glue pieces back with a separator
        String csv = "red,green,blue";
        String[] colors = csv.split(",");
        System.out.println("split -> " + colors.length + " parts, join -> " + String.join(" | ", colors));

        // immutability: the old string stays the same unless you store the new one back
        String name = "aditya";
        name.toUpperCase();                       // makes "ADITYA" but we did not store it, so it is lost
        System.out.println("After toUpperCase() without assigning: " + name);
        name = name.toUpperCase();                // now we store the new string back in name
        System.out.println("After name = name.toUpperCase():       " + name);

        // == vs equals
        //   ==       checks "is it the SAME object in memory?"
        //   equals() checks "is the TEXT the same?"  <- this is almost always what you want
        String a = "java";                        // text in quotes is saved once in the "String pool" and reused
        String b = "java";                        // so b gets the same saved object as a
        String c = new String("java");            // new always makes a fresh separate object
        System.out.println("a == b      : " + (a == b));
        System.out.println("a == c      : " + (a == c) + "   <- different objects");
        System.out.println("a.equals(c) : " + a.equals(c) + "    <- same text: use this");
        System.out.println("equalsIgnoreCase: " + "JAVA".equalsIgnoreCase(a));          // same text, ignoring capital/small letters

        // safe comparison with null: write the fixed text first
        // "yes".equals(input) is safe. input.equals("yes") would crash when input is null
        String input = null;
        System.out.println("\"yes\".equals(null) : " + "yes".equals(input));   // no NullPointerException

        // formatting: %-8s = text in 8 spaces (left side), %5.1f = decimal with 1 digit after the point, %% = the % sign
        System.out.println(String.format("%-8s scored %5.1f%%", "Aditya", 92.456));
    }
}
