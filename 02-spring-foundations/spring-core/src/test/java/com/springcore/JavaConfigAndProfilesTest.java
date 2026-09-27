package com.springcore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.springcore.topic11_java_config.AppConfig;
import com.springcore.topic11_java_config.Car;
import com.springcore.topic11_java_config.Engine;
import com.springcore.topic12_properties_and_profiles.DataStore;
import com.springcore.topic12_properties_and_profiles.DatabaseDataStore;
import com.springcore.topic12_properties_and_profiles.InMemoryDataStore;
import com.springcore.topic12_properties_and_profiles.ProfilesConfig;

class JavaConfigAndProfilesTest {

    @Nested
    @SpringJUnitConfig(AppConfig.class)
    class JavaConfig {

        @Autowired
        Car car;

        @Autowired
        Engine engine;

        @Test
        void beanMethodCallsReturnTheSingleton() {
            assertSame(engine, car.getEngine());
        }
    }

    @Nested
    @SpringJUnitConfig(ProfilesConfig.class)
    @ActiveProfiles("dev")
    class DevProfile {

        @Autowired
        DataStore dataStore;

        @Autowired
        Environment environment;

        @Test
        void devProfileUsesTheInMemoryStore() {
            assertInstanceOf(InMemoryDataStore.class, dataStore);
        }

        @Test
        void propertiesFileIsLoaded() {
            assertEquals("Welcome to Learning Portal!", environment.getProperty("app.greeting"));
        }
    }

    @Nested
    @SpringJUnitConfig(ProfilesConfig.class)
    @ActiveProfiles("prod")
    class ProdProfile {

        @Autowired
        DataStore dataStore;

        @Test
        void prodProfileUsesTheDatabaseStore() {
            assertInstanceOf(DatabaseDataStore.class, dataStore);
        }
    }
}
