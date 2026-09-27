package com.springcore.topic12_properties_and_profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * ${key} looks a value up in the Environment (property files, system properties,
 * environment variables). ${key:default} supplies a fallback when the key is missing.
 * Spring converts the text to the field type (here String and int).
 */
@Component
public class AppProperties {

    @Value("${app.name}")
    private String name;

    @Value("${app.max-users}")
    private int maxUsers;

    @Value("${app.greeting}")
    private String greeting;

    @Value("${app.timeout-seconds:30}")      // not in app.properties -> 30
    private int timeoutSeconds;

    @Override
    public String toString() {
        return "name=" + name + ", maxUsers=" + maxUsers + ", timeoutSeconds=" + timeoutSeconds + ", greeting='" + greeting + "'";
    }
}
