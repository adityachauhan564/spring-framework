package topic44_file_io.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Solutions for topic44_file_io/Exercises.java
public class ExercisesSolution {

    static int countLinesContaining(Path file, String word) throws IOException {
        String lowerWord = word.toLowerCase();
        int count = 0;
        try (BufferedReader reader = Files.newBufferedReader(file)) {    // closed even if reading fails
            String line;
            while ((line = reader.readLine()) != null) {                // one line in memory at a time
                if (line.toLowerCase().contains(lowerWord)) {
                    count++;
                }
            }
        }
        return count;
    }

    static void saveSettings(Path file, Map<String, String> settings) throws IOException {
        List<String> lines = new TreeMap<>(settings).entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .toList();
        Files.write(file, lines);                                        // creates or replaces
    }

    static void appendLog(Path file, String message) throws IOException {
        Files.writeString(file, message + System.lineSeparator(),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);   // CREATE: fine if it doesn't exist
    }

    public static void main(String[] args) throws IOException {
        Path folder = Files.createTempDirectory("exercises");
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
