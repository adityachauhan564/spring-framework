package com.springcore.jdbc.topic01_datasource_and_jdbctemplate;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/*
 * Run      : ./mvnw -q -pl spring-jdbc compile exec:java -Dexec.mainClass=com.springcore.jdbc.topic01_datasource_and_jdbctemplate.JdbcTemplateDemo
 * Key idea : - update() is for INSERT / UPDATE / DELETE. It returns how many rows changed.
 *            - queryForObject() is for reading a single value.
 *            - Always use ? placeholders for values. Never build SQL by joining strings with +.
 *              That opens the door to SQL injection (a user typing SQL into a form to attack your database).
 * Try this : Insert the same id twice and read the DuplicateKeyException.
 *            Spring turned the database's own SQLException (different for every database)
 *            into a common DataAccessException that is the same for all databases.
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
