package com.learning.irctc.store;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.learning.irctc.entities.Train;
import com.learning.irctc.entities.User;

/*
 * Two JSON files used as a tiny "database": users.json and trains.json in a data folder.
 *
 * The starting data comes inside the jar (src/main/resources/localDb) and is read as a CLASSPATH
 * resource, which works from any folder. A jar cannot be written to, so on the first run the
 * files are copied to the data folder, and every later read and write uses that copy.
 * (The course read "src/main/resources/..." by relative path, which only works from one folder.)
 */
public class JsonStore {

    private static final TypeReference<List<User>> USERS = new TypeReference<>() {};    // the {} keeps the full type List<User>
    private static final TypeReference<List<Train>> TRAINS = new TypeReference<>() {};  // even though Java erases generic types at runtime

    private final ObjectMapper mapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)   // trainNo <-> "train_no"
            .registerModule(new JavaTimeModule())                              // LocalDate <-> "2026-10-01"
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(SerializationFeature.INDENT_OUTPUT);

    private final Path usersFile;
    private final Path trainsFile;

    public JsonStore(Path dataDir) {
        this.usersFile = dataDir.resolve("users.json");
        this.trainsFile = dataDir.resolve("trains.json");
        try {
            Files.createDirectories(dataDir);
            copyDefaultIfMissing("users.json", usersFile);
            copyDefaultIfMissing("trains.json", trainsFile);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot prepare data folder " + dataDir, e);
        }
    }

    public List<User> loadUsers() {
        return read(usersFile, USERS);
    }

    public List<Train> loadTrains() {
        return read(trainsFile, TRAINS);
    }

    public void saveUsers(List<User> users) {
        write(usersFile, users);
    }

    public void saveTrains(List<Train> trains) {
        write(trainsFile, trains);
    }

    private <T> List<T> read(Path file, TypeReference<List<T>> type) {
        try {
            return new ArrayList<>(mapper.readValue(file.toFile(), type));   // a copy that we are allowed to change
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot read " + file, e);
        }
    }

    // Write to a temporary file first, then move it over the old one. A crash in the middle can't leave half a file
    private void write(Path file, Object value) {
        try {
            Path temp = file.resolveSibling(file.getFileName() + ".tmp");
            mapper.writeValue(temp.toFile(), value);
            Files.move(temp, file, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot write " + file, e);
        }
    }

    private static void copyDefaultIfMissing(String name, Path target) throws IOException {
        if (Files.exists(target)) return;
        try (InputStream in = JsonStore.class.getResourceAsStream("/localDb/" + name)) {
            if (in == null) throw new IOException("Missing classpath resource /localDb/" + name);
            Files.copy(in, target);
        }
    }
}
