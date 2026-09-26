package topic23_file_io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/*
 * Topic    : Reading and writing files with java.nio.file
 * Key idea : Path points to a file; the Files class does the work.
 *            Small files: readAllLines / writeString. Big files: a BufferedReader line by line.
 *            File operations throw IOException (checked - see topic06).
 * Run      : java -cp out topic23_file_io.FileIODemo
 * Try this : count how many lines contain the word "Java".
 */
public class FileIODemo {

    public static void main(String[] args) {
        Path file = null;
        try {
            // a temp file keeps the demo from leaving files in your project
            file = Files.createTempFile("notes", ".txt");

            // write (creates or replaces)
            Files.writeString(file, "Java is fun\nFiles are easy\n");

            // append
            Files.writeString(file, "Appended line\n", StandardOpenOption.APPEND);

            // write many lines with a BufferedWriter
            try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.APPEND)) {
                writer.write("Line from BufferedWriter");
                writer.newLine();
            }

            // read everything at once (fine for small files)
            List<String> lines = Files.readAllLines(file);
            System.out.println("readAllLines -> " + lines.size() + " lines: " + lines);

            // read line by line (works for any size)
            try (BufferedReader reader = Files.newBufferedReader(file)) {
                String line;
                int number = 1;
                while ((line = reader.readLine()) != null) {     // null = end of file
                    System.out.println("  " + number++ + ": " + line);
                }
            }

            System.out.println("exists: " + Files.exists(file) + ", size: " + Files.size(file) + " bytes");
            System.out.println("file name: " + file.getFileName() + ", folder: " + file.getParent());

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        } finally {
            if (file != null) {
                try {
                    Files.deleteIfExists(file);
                    System.out.println("temp file deleted");
                } catch (IOException e) {
                    System.out.println("Could not delete temp file: " + e.getMessage());
                }
            }
        }
    }
}
