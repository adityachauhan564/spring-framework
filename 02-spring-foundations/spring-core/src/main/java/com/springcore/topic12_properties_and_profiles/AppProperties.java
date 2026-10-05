package com.springcore.topic12_properties_and_profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * ${key} looks up a value in the Environment.
 * (The Environment = all the places Spring reads settings from: property files,
 * system properties, and environment variables.)
 * ${key:default} gives a backup value when the key is missing.
 * Spring also converts the text into the field's type (here String and int).
 */
@Component
public class AppProperties {

    @Value("${app.name}")
    private String name;

    @Value("${app.max-users}")
    private int maxUsers;

    @Value("${app.greeting}")
    private String greeting;

    @Value("${app.timeout-seconds:30}")      // this key is not in app.properties, so the default 30 is used
    private int timeoutSeconds;

    @Override
    public String toString() {
        return "name=" + name + ", maxUsers=" + maxUsers + ", timeoutSeconds=" + timeoutSeconds + ", greeting='" + greeting + "'";
    }
}
