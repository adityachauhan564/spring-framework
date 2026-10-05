package com.springcore.topic12_properties_and_profiles;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/*
 * Topic    : Externalized configuration - properties, Environment and profiles
 * Key idea : Settings live outside the code, so you can change them without recompiling.
 *            1. Values come from OUTSIDE the code (@PropertySource, ${...}).
 *            2. System properties and environment variables win over the file.
 *            3. A profile switches whole beans on or off for each environment (dev / prod).
 *            - Like a mobile phone's modes: same phone, but "silent" and "outdoor" behave differently.
 *            - Spring Boot is built on exactly this: application.properties + spring.profiles.active.
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic12_properties_and_profiles.PropertiesAndProfilesDemo
 * Try this : Add -Dapp.name=MyApp to the command - the system property wins over app.properties.
 */
public class PropertiesAndProfilesDemo {

    public static void main(String[] args) {
        for (String profile : new String[] {"dev", "prod"}) {
            try (var context = new AnnotationConfigApplicationContext()) {
                context.getEnvironment().setActiveProfiles(profile);   // must be set BEFORE refresh (refresh creates the beans)
                context.register(ProfilesConfig.class);
                context.refresh();

                Environment env = context.getEnvironment();
                System.out.println("Active profiles: " + Arrays.toString(env.getActiveProfiles()));
                System.out.println("  @Value fields:       " + context.getBean(AppProperties.class));
                System.out.println("  Environment lookup:  app.name = " + env.getProperty("app.name")
                        + ", missing key -> " + env.getProperty("app.missing", "fallback"));
                System.out.println("  DataStore bean:      " + context.getBean(DataStore.class).describe());
            }
        }
    }
}
