package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class AppTest {
    // Guards the local JSON "database" against typos/bad edits.
    @Test public void localDbFilesAreValidJsonArrays() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        for (String name : new String[]{"users.json", "trains.json"}) {
            JsonNode node = mapper.readTree(new File("src/main/resources/localDb/" + name));
            assertTrue(name + " should be a JSON array", node.isArray());
        }
    }
}
