package com.springcore.topic11_java_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/*
 * @Configuration + @Bean = the Java version of an XML file with <bean> elements.
 * The bean name is the METHOD name, so name methods after the bean (engine, not getEngine).
 * Add @ComponentScan("some.package") here to also pick up @Component classes.
 */
@Configuration
@Import(DealershipConfig.class)
public class AppConfig {

    @Bean
    public Engine engine() {
        return new Engine();
    }

    @Bean
    public Car car() {
        // Looks like it creates a second Engine - but Spring wraps this class in a proxy,
        // so engine() returns the one singleton Engine bean. JavaConfigDemo proves it.
        return new Car(engine());
    }
}
