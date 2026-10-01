package topic44_file_io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/*
 * Exercises for topic 44.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic44_file_io.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. How many lines of the file contain the word? Ignore capital/small letters.
    //    Read it line by line using Files.newBufferedReader inside try-with-resources,
    //    so it works even for a very big file.
    static int countLinesContaining(Path file, String word) throws IOException {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Write one "key=value" line for each map entry, sorted by key.
    //    If the file already exists, replace it.
    static void saveSettings(Path file, Map<String, String> settings) throws IOException {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Add one line at the END of a log file. If the file doesn't exist yet, create it.
    static void appendLog(Path file, String message) throws IOException {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) throws IOException {
        Path folder = Files.createTempDirectory("exercises");       // a temporary folder - nothing is left in your project
        try {
            Path notes = folder.resolve("notes.txt");
            Files.writeString(notes, "Java is fun\nstreams are fun\nI like JAVA\n");
            check(countLinesContaining(notes, "java") == 2, "exercise 1");

            Path settings = folder.resolve("app.properties");
            saveSettings(settings, Map.of("port", "8080", "mode", "dev"));
            check(Files.readAllLines(settings).equals(List.of("mode=dev", "port=8080")), "exercise 2");

            Path log = folder.resolve("app.log");
            appendLog(log, "started");
            appendLog(log, "stopped");
            check(Files.readAllLines(log).equals(List.of("started", "stopped")), "exercise 3");
            System.out.println("All exercises pass");
        } finally {
            // clean up: delete every file in the temporary folder, then the folder itself
            try (var files = Files.list(folder)) {
                for (Path p : files.toList()) {
                    Files.delete(p);
                }
            }
            Files.delete(folder);
        }
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
