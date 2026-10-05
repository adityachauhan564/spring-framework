package com.springcore.topic12_properties_and_profiles;

/*
 * One interface, and one implementation for each environment (dev, prod).
 * The active profile decides which of the two beans gets created.
 */
public interface DataStore {

    String describe();
}
