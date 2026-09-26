package topic06_exceptions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Topic    : Checked exceptions (Head First Java, chapter 11)
 * Key idea : the compiler FORCES you to handle a checked exception
 *            (catch it, or declare 'throws'). Remove the try/catch below
 *            and the file won't compile.
 * Run      : java -cp out topic06_exceptions.CheckedExceptionDemo
 * Try this : create myFile.txt in the folder you run 'java' from, then run again.
 */
public class CheckedExceptionDemo {

    public static void main(String[] args) {
        readFirstLine("myFile.txt");
    }

    private static void readFirstLine(String fileName) {
        // try-with-resources: the reader is closed automatically, even on error
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            System.out.println("First line: " + reader.readLine());
        } catch (FileNotFoundException e) {        // most specific exception first
            System.out.println("File doesn't exist: " + fileName);
        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }
    }
}
