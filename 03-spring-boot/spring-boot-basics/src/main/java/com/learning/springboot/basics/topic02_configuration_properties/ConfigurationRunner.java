package com.learning.springboot.basics.topic02_configuration_properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
 * @Value vs @ConfigurationProperties:
 *   @Value("${app.name}")   - fine for ONE value. The key is just a string, checked only when the app runs
 *   ShopProperties          - a typed group of values, validated, easy to pass around and to test
 * Where values come from - the ones further right WIN:
 *   application.yml < application-<profile>.yml < environment variables < command-line args
 * Try this : APP_SHOP_CURRENCY=USD ./mvnw spring-boot:run   (an environment variable, in the relaxed name style)
 *            ./mvnw spring-boot:run -Dspring-boot.run.arguments=--app.shop.currency=EUR
 */
@Component
@Order(3)
public class ConfigurationRunner implements ApplicationRunner {

    private final String appName;
    private final ShopProperties shop;

    public ConfigurationRunner(@Value("${app.name}") String appName, ShopProperties shop) {
        this.appName = appName;
        this.shop = shop;
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("\n=== topic02: configuration properties ===");
        System.out.println("@Value(\"${app.name}\")   -> " + appName);
        System.out.println("@ConfigurationProperties -> currency=" + shop.currency() + ", maxItems=" + shop.maxItems()
                + ", discount=" + shop.discountPercent() + "%, contact=" + shop.contact().email());
    }
}
