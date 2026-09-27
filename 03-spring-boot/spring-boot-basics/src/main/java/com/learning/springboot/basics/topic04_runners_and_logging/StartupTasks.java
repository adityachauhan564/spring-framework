package com.learning.springboot.basics.topic04_runners_and_logging;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/*
 * Topic    : Code that runs once at startup, and proper logging
 * Key idea : CommandLineRunner gets the raw String[] args; ApplicationRunner gets them parsed
 *            (--name=Asha is an "option", anything else a "non-option" argument).
 *            Both run after the application has fully started; @Order sets their sequence.
 *            Log with SLF4J instead of System.out: every line gets a time, level and class, and
 *            levels can be turned up or down per package without touching code
 *            (logging.level.<package>=debug in application.yml or on the command line).
 * Try this : ./mvnw spring-boot:run -Dspring-boot.run.arguments="--name=Asha extra --logging.level.com.learning.springboot.basics.topic04_runners_and_logging=debug"
 *            (the other runners print with System.out only to keep the topic output easy to read)
 */
@Configuration
public class StartupTasks {

    private static final Logger log = LoggerFactory.getLogger(StartupTasks.class);

    @Bean
    @Order(5)
    CommandLineRunner commandLineRunner() {
        return args -> log.info("=== topic04 === CommandLineRunner, raw args: {}", Arrays.toString(args));
    }

    @Bean
    @Order(6)
    ApplicationRunner applicationRunner() {
        return (ApplicationArguments args) -> {
            String name = args.containsOption("name") ? args.getOptionValues("name").get(0) : "stranger";
            log.info("ApplicationRunner, --name option = {}, other args = {}", name, args.getNonOptionArgs());
            log.debug("this DEBUG line appears only when this package's log level is debug");
        };
    }
}
