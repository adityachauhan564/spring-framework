package topic01_first_program;

/*
 * Topic    : Compile-time vs runtime errors
 * Key idea : the COMPILER (javac) rejects code that breaks Java's rules, before anything runs.
 *            Code that compiles can still fail while RUNNING (java), when a value is wrong.
 *            Compile errors are the friendly kind: the message gives the file, the line and the reason.
 * Run      : java -cp out topic01_first_program.CompileVsRuntimeErrors
 * Try this : un-comment one broken line at a time, compile, and read the message.
 */
public class CompileVsRuntimeErrors {

    public static void main(String[] args) {
        System.out.println("This program compiles and runs.");

        // --- compile-time errors: javac refuses the file ---
        // System.out.println("missing semicolon")          error: ';' expected
        // system.out.println("wrong case");                error: package system does not exist (Java is case-sensitive)
        // int count = "five";                              error: incompatible types: String cannot be converted to int
        // System.out.println(undefinedName);               error: cannot find symbol

        // --- runtime errors: it compiles, then crashes while running ---
        int[] numbers = {10, 20, 30};
        System.out.println("numbers[2] = " + numbers[2]);
        // System.out.println(numbers[3]);                  ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
        // System.out.println(10 / 0);                      ArithmeticException: / by zero

        System.out.println("A runtime error prints a 'stack trace': the exception name, the message,");
        System.out.println("and the line number where it happened. Read it from the top.");
    }
}
