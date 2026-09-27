package com.learning.springboot.basics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.validation.autoconfigure.ValidationAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.learning.springboot.basics.topic01_how_boot_starts.FormalGreeting;
import com.learning.springboot.basics.topic01_how_boot_starts.FriendlyGreeting;
import com.learning.springboot.basics.topic01_how_boot_starts.GreetingService;
import com.learning.springboot.basics.topic02_configuration_properties.ShopProperties;

/*
 * ApplicationContextRunner starts a tiny, fast context with only the classes and properties
 * you give it - the standard way to test conditions and configuration binding.
 */
class ConditionsAndPropertiesTest {

    private final ApplicationContextRunner greetings = new ApplicationContextRunner()
            .withUserConfiguration(FriendlyGreeting.class, FormalGreeting.class);

    @Test
    void friendlyGreetingIsTheDefaultWhenThePropertyIsMissing() {
        greetings.run(context -> assertInstanceOf(FriendlyGreeting.class, context.getBean(GreetingService.class)));
    }

    @Test
    void propertySwitchesTheBean() {
        greetings.withPropertyValues("app.greeting.style=formal")
                .run(context -> assertInstanceOf(FormalGreeting.class, context.getBean(GreetingService.class)));
    }

    @Configuration
    @EnableConfigurationProperties(ShopProperties.class)
    static class ShopConfig {
    }

    private final ApplicationContextRunner shop = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(ValidationAutoConfiguration.class))
            .withUserConfiguration(ShopConfig.class)
            .withPropertyValues("app.shop.currency=INR", "app.shop.max-items=20", "app.shop.contact.email=a@example.com");

    @Test
    void relaxedBindingFillsTheRecord() {
        shop.withPropertyValues("app.shop.discount-percent=10").run(context -> {
            ShopProperties properties = context.getBean(ShopProperties.class);
            assertEquals(20, properties.maxItems());       // max-items -> maxItems
            assertEquals("a@example.com", properties.contact().email());
        });
    }

    @Test
    void invalidValueStopsTheApplicationAtStartup() {
        shop.withPropertyValues("app.shop.discount-percent=95").run(context -> {
            assertNotNull(context.getStartupFailure());
            assertTrue(context.getStartupFailure().getMessage().contains("app.shop"));
        });
    }
}
