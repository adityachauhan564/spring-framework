package topic01_first_program;

/*
 * Topic    : Compile-time errors vs runtime errors
 * Key idea : There are two kinds of mistakes:
 *              1. Compile-time error - javac checks your code BEFORE it runs. If you break
 *                 a Java rule (like a missing ;), it refuses to make the .class file.
 *              2. Runtime error - the code compiles fine, but crashes WHILE running,
 *                 because some value is wrong (like dividing by zero).
 *            Compile errors are the easy ones - the message tells you the file, the line
 *            and the reason. Like a teacher marking your copy before the exam.
 * Run      : java -cp out topic01_first_program.CompileVsRuntimeErrors
 * Try this : Remove the // from ONE broken line at a time, compile, and read the message.
 */
public class CompileVsRuntimeErrors {

    public static void main(String[] args) {
        System.out.println("This program compiles and runs.");

        // --- compile-time errors: javac will not even make the .class file ---
        // System.out.println("missing semicolon")          error: ';' expected
        // system.out.println("wrong case");                error: package system does not exist (Java is case-sensitive: S and s are different)
        // int count = "five";                              error: incompatible types: String cannot be converted to int
        // System.out.println(undefinedName);               error: cannot find symbol (this name was never created)

        // --- runtime errors: it compiles, then crashes while running ---
        int[] numbers = {10, 20, 30};
        System.out.println("numbers[2] = " + numbers[2]);
        // System.out.println(numbers[3]);                  ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
        // System.out.println(10 / 0);                      ArithmeticException: / by zero

        System.out.println("A runtime error prints a 'stack trace': the exception name, the message,");
        System.out.println("and the line number where it happened. Read it from the top.");
    }
}
