package com.springcore.topic12_properties_and_profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/* Created only when the "prod" profile is active. (Just a stand-in: there is no real database here.) */
@Component
@Profile("prod")
public class DatabaseDataStore implements DataStore {

    @Override
    public String describe() {
        return "database store (persistent - for production)";
    }
}
