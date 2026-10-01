package topic44_file_io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/*
 * Topic    : Reading and writing files with java.nio.file
 * Key idea : - A Path is the ADDRESS of a file. The Files class does the actual work.
 *            - Small file? Read or write it all at once: readAllLines / writeString.
 *            - Big file (like a 2 GB log)? Read it line by line with a BufferedReader,
 *              so only one line is in memory at a time. Like reading a long novel page by page,
 *              instead of trying to hold the whole book open at once.
 *            - File work can fail (no file, no permission), so it throws IOException -
 *              a checked exception (see topic 10).
 * Run      : java -cp out topic44_file_io.FileIODemo
 * Try this : Count how many lines contain the word "Java".
 */
public class FileIODemo {

    public static void main(String[] args) {
        Path file = null;
        try {
            // a temporary file, so this demo doesn't leave junk files in your project
            file = Files.createTempFile("notes", ".txt");

            // write - creates the file, or replaces everything already in it
            Files.writeString(file, "Java is fun\nFiles are easy\n");

            // append - adds at the end, keeps what is already there
            Files.writeString(file, "Appended line\n", StandardOpenOption.APPEND);

            // write lines with a BufferedWriter (good for writing many lines)
            try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.APPEND)) {
                writer.write("Line from BufferedWriter");
                writer.newLine();
            }   // try-with-resources closes the writer here, automatically

            // read everything at once (fine for small files)
            List<String> lines = Files.readAllLines(file);
            System.out.println("readAllLines -> " + lines.size() + " lines: " + lines);

            // read line by line (works for files of any size)
            try (BufferedReader reader = Files.newBufferedReader(file)) {
                String line;
                int number = 1;
                while ((line = reader.readLine()) != null) {     // readLine() gives null at the end of the file
                    System.out.println("  " + number++ + ": " + line);
                }
            }

            System.out.println("exists: " + Files.exists(file) + ", size: " + Files.size(file) + " bytes");
            System.out.println("file name: " + file.getFileName() + ", folder: " + file.getParent());

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        } finally {
            // clean up: delete the temporary file, whether things went well or not
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
