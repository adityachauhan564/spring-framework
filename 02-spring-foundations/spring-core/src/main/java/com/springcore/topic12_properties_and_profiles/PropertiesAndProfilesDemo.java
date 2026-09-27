package com.springcore.topic12_properties_and_profiles;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/*
 * Topic    : Externalized configuration - properties, Environment and profiles
 * Key idea : 1. values come from OUTSIDE the code (@PropertySource, ${...});
 *            2. system properties and environment variables override the file;
 *            3. a profile switches whole beans on or off per environment (dev / prod).
 *            Spring Boot builds on exactly this: application.properties + spring.profiles.active.
 * Run      : ./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic12_properties_and_profiles.PropertiesAndProfilesDemo
 * Try this : add -Dapp.name=MyApp to the command - the system property wins over app.properties.
 */
public class PropertiesAndProfilesDemo {

    public static void main(String[] args) {
        for (String profile : new String[] {"dev", "prod"}) {
            try (var context = new AnnotationConfigApplicationContext()) {
                context.getEnvironment().setActiveProfiles(profile);   // must happen BEFORE refresh
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
