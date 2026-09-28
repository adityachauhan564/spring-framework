package topic01_first_program;

/*
 * Topic    : Your first program
 * Key idea : javac turns HelloWorld.java into HelloWorld.class (bytecode); java runs that
 *            bytecode on the JVM, starting at main. Every Java program starts at a main method.
 * Run      : java -cp out topic01_first_program.HelloWorld
 * Try this : change the message, compile again, run again. Then remove a semicolon and read the error.
 */
public class HelloWorld {                  // public class name == file name (HelloWorld.java)

    // main: the entry point the JVM looks for. String[] args = words typed after the class name.
    public static void main(String[] args) {
        System.out.println("Hello, World!");        // println prints the text, then a new line
        System.out.print("print stays ");           // print does NOT add a new line
        System.out.println("on the same line.");

        // a single-line comment: ignored by the compiler
        /* a multi-line comment,
           also ignored */

        System.out.println("You passed " + args.length + " argument(s)");
        // try:  java -cp out topic01_first_program.HelloWorld one two
    }
}
