package com.springcore.topic12_properties_and_profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/* Only created when the "dev" profile is active. */
@Component
@Profile("dev")
public class InMemoryDataStore implements DataStore {

    @Override
    public String describe() {
        return "in-memory store (fast, empty on every start - good for development)";
    }
}
