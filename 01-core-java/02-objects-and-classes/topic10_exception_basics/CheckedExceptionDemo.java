package topic10_exception_basics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Topic    : Checked exceptions (from Head First Java, chapter 11)
 * Key idea : An exception is Java's way of saying "something went wrong".
 *            A CHECKED exception is a problem Java knows can happen outside your control -
 *            like a file that is missing. The compiler FORCES you to plan for it:
 *              - either catch it (try/catch), or
 *              - write 'throws' and pass the problem to whoever called you.
 *            Like a train ticket booking: the app must handle "payment failed", it can't ignore it.
 *            Remove the try/catch below and the file will not compile.
 * Run      : java -cp out topic10_exception_basics.CheckedExceptionDemo
 * Try this : Create myFile.txt in the folder you run 'java' from, and run again.
 */
public class CheckedExceptionDemo {

    public static void main(String[] args) {
        readFirstLine("myFile.txt");
    }

    private static void readFirstLine(String fileName) {
        // try-with-resources: the reader inside ( ) is closed automatically at the end,
        // even if an error happens. No need to remember to close it yourself.
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            System.out.println("First line: " + reader.readLine());
        } catch (FileNotFoundException e) {        // put the more specific exception first...
            System.out.println("File doesn't exist: " + fileName);
        } catch (IOException e) {                  // ...and the more general one after it
            System.out.println("Could not read file: " + e.getMessage());
        }
    }
}
