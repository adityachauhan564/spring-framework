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
 * Key idea : Runners = code that runs ONCE, right after the app has fully started.
 *            - CommandLineRunner gets the raw String[] args.
 *            - ApplicationRunner gets them already sorted out
 *              (--name=Asha is an "option", anything else is a "non-option" argument).
 *            - @Order decides which runner goes first.
 *            - Like a shopkeeper's morning routine after opening: light a diya, count the cash, then start.
 *            Log with SLF4J instead of System.out:
 *            - every line gets a time, a level (INFO, DEBUG...) and the class name;
 *            - you can turn the detail up or down for each package without touching code
 *              (logging.level.<package>=debug in application.yml or on the command line).
 * Try this : ./mvnw spring-boot:run -Dspring-boot.run.arguments="--name=Asha extra --logging.level.com.learning.springboot.basics.topic04_runners_and_logging=debug"
 *            (the other runners use System.out only to keep each topic's output easy to read)
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
