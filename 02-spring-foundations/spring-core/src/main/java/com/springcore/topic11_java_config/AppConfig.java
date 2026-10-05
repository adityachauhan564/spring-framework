package com.springcore.topic11_java_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/*
 * @Configuration + @Bean = the Java version of an XML file full of <bean> elements.
 * Each @Bean method returns one object, and Spring keeps it as a bean.
 * The bean name is the METHOD name, so name the method after the bean (engine, not getEngine).
 * Add @ComponentScan("some.package") here if you also want Spring to pick up @Component classes.
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
        // This looks like it creates a second Engine - but it doesn't.
        // Spring wraps this class in a proxy (a stand-in object that sits in front of it),
        // so engine() returns the same single Engine bean every time. JavaConfigDemo proves it.
        return new Car(engine());
    }
}
