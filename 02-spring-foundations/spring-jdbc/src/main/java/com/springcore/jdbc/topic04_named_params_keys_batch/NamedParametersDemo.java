package com.springcore.jdbc.topic04_named_params_keys_batch;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springcore.jdbc.topic01_datasource_and_jdbctemplate.JdbcConfig;

/*
 * Run      : ./mvnw -q -pl spring-jdbc compile exec:java -Dexec.mainClass=com.springcore.jdbc.topic04_named_params_keys_batch.NamedParametersDemo
 * Key idea : NamedParameterJdbcTemplate = JdbcTemplate with :names instead of ? positions.
 *            KeyHolder gives you the generated id; batchUpdate sends many rows in one go.
 * Try this : add findByTitleLike(String text) using "where title like :pattern".
 */
public class NamedParametersDemo {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(JdbcConfig.class, CourseRepository.class)) {
            CourseRepository courses = context.getBean(CourseRepository.class);

            Course spring = courses.save(Course.unsaved("Spring", 9000));
            Course hibernate = courses.save(Course.unsaved("Hibernate", 2000));
            System.out.println("saved with generated ids: " + spring + ", " + hibernate);

            int inserted = courses.saveAll(List.of(
                    Course.unsaved("Docker", 3000),
                    Course.unsaved("Kubernetes", 7000),
                    Course.unsaved("AWS", 12000)));
            System.out.println("batch inserted " + inserted + " courses in one call");

            System.out.println("fee between 2500 and 9000: " + courses.findByFeeBetween(2500, 9000));
        }
    }
}
