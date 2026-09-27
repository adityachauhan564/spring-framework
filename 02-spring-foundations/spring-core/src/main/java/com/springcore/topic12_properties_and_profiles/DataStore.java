package com.springcore.topic12_properties_and_profiles;

/* One interface, one implementation per environment - the active profile picks which bean exists. */
public interface DataStore {

    String describe();
}
