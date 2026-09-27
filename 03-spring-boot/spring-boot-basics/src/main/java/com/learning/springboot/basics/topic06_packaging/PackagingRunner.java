package com.learning.springboot.basics.topic06_packaging;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.learning.springboot.basics.BasicsApplication;

/*
 * Topic    : Packaging - one executable jar with the server inside
 * Key idea : ./mvnw package builds target/spring-boot-basics-0.0.1-SNAPSHOT.jar containing your
 *            classes, every dependency AND the embedded Tomcat. Deploying = copying one file
 *            and running `java -jar` - no server to install (compare the WAR in stage 02).
 *            The build-info goal in pom.xml records the version and build time, which Boot
 *            exposes as a BuildProperties bean and in /actuator/info.
 * Try this : ./mvnw package  then  java -jar target/spring-boot-basics-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
 */
@Component
@Order(7)
public class PackagingRunner implements ApplicationRunner {

    private final ObjectProvider<BuildProperties> buildProperties;

    public PackagingRunner(ObjectProvider<BuildProperties> buildProperties) {
        this.buildProperties = buildProperties;   // missing when build-info hasn't run (e.g. some IDE runs)
    }

    @Override
    public void run(ApplicationArguments args) {
        String location = BasicsApplication.class.getProtectionDomain().getCodeSource().getLocation().toString();
        System.out.println("\n=== topic06: packaging ===");
        System.out.println("running from: " + (location.contains(".jar") ? "an executable jar (java -jar)" : "compiled classes (IDE or spring-boot:run)"));
        BuildProperties build = buildProperties.getIfAvailable();
        System.out.println("build info:   " + (build == null ? "not generated - run ./mvnw package"
                : build.getArtifact() + " " + build.getVersion() + ", built " + build.getTime()));
        System.out.println("\nApp is up: try http://localhost:8080/actuator/health  (Ctrl+C to stop)");
    }
}
