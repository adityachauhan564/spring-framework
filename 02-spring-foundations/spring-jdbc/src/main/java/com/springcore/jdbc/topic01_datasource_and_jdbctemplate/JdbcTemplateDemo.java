package com.springcore.jdbc.topic01_datasource_and_jdbctemplate;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/*
 * Run      : ./mvnw -q -pl spring-jdbc compile exec:java -Dexec.mainClass=com.springcore.jdbc.topic01_datasource_and_jdbctemplate.JdbcTemplateDemo
 * Key idea : update() for INSERT/UPDATE/DELETE (returns the number of changed rows),
 *            queryForObject() for a single value. Always use ? placeholders - never
 *            build SQL by string concatenation (that invites SQL injection).
 * Try this : insert the same id twice and read the DuplicateKeyException - Spring turned the
 *            vendor-specific SQLException into a portable DataAccessException.
 */
public class JdbcTemplateDemo {

    public static void main(String[] args) throws SQLException {
        try (var context = new AnnotationConfigApplicationContext(JdbcConfig.class)) {
            DataSource dataSource = context.getBean(DataSource.class);
            try (Connection connection = dataSource.getConnection()) {
                System.out.println("Connected to: " + connection.getMetaData().getDatabaseProductName());
            }

            JdbcTemplate jdbc = context.getBean(JdbcTemplate.class);

            int inserted = jdbc.update("insert into student (id, name, city) values (?, ?, ?)", 10, "Khushi Chauhan", "Lucknow");
            System.out.println("rows inserted: " + inserted);

            Integer count = jdbc.queryForObject("select count(*) from student", Integer.class);
            String name = jdbc.queryForObject("select name from student where id = ?", String.class, 10);
            System.out.println("students in table: " + count + ", student 10 is " + name);
        }
    }
}
