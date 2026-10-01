package topic01_first_program;

/*
 * Topic    : Your first program
 * Key idea : Two steps to run any Java program:
 *              1. javac (the compiler) reads HelloWorld.java and makes HelloWorld.class.
 *                 This .class file has "bytecode" - instructions for the JVM, not for you.
 *              2. java runs that .class file on the JVM (Java Virtual Machine).
 *            The JVM always starts from the main method. No main = nothing runs.
 * Run      : java -cp out topic01_first_program.HelloWorld
 * Try this : Change the message, compile again, run again.
 *            Then remove one semicolon (;) and read the error message.
 */
public class HelloWorld {                  // a public class must have the same name as its file (HelloWorld.java)

    // main is the starting point. The JVM looks for exactly this line.
    // String[] args = extra words you type after the class name while running.
    public static void main(String[] args) {
        System.out.println("Hello, World!");        // println = print the text, then go to the next line
        System.out.print("print stays ");           // print = print the text, but stay on the SAME line
        System.out.println("on the same line.");

        // this is a single-line comment - Java skips it completely
        /* this is a multi-line comment,
           Java skips this also */

        System.out.println("You passed " + args.length + " argument(s)");
        // try this:  java -cp out topic01_first_program.HelloWorld one two   (it will say 2)
    }
}
