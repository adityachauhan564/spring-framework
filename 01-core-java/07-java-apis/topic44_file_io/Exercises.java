package topic44_file_io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/*
 * Exercises for topic 44. Replace each "TODO" line with your code, then run:
 *   java -cp out topic44_file_io.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. How many lines of the file contain the word (case-insensitive)?
    //    Read it line by line with Files.newBufferedReader in try-with-resources, so any size works.
    static int countLinesContaining(Path file, String word) throws IOException {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Write one "key=value" line per map entry, sorted by key, replacing the file if it exists.
    static void saveSettings(Path file, Map<String, String> settings) throws IOException {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Append one line to the end of a log file, creating the file if it doesn't exist yet.
    static void appendLog(Path file, String message) throws IOException {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) throws IOException {
        Path folder = Files.createTempDirectory("exercises");       // nothing is left in your project
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
            try (var files = Files.list(folder)) {
                for (Path p : files.toList()) {
                    Files.delete(p);
                }
            }
            Files.delete(folder);
        }
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
