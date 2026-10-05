package com.springboot.udemy.ranga.topic01_spring_jdbc;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springboot.udemy.ranga.topic02_jpa_entity_manager.Course;

/*
 * Topic    : Spring JDBC with Spring Boot - you write the SQL
 * Key idea : - This is exactly the JdbcTemplate from 02-spring-foundations/spring-jdbc.
 *              But there you wrote the DataSource and JdbcTemplate beans yourself.
 *            - Here Boot saw H2 on the classpath and created both by itself
 *              (plus a HikariCP connection pool), and also ran schema.sql.
 *            - Text blocks (""") keep multi-line SQL easy to read. ? placeholders keep it safe.
 */
@Repository
public class CourseJdbcRepository {

    private static final String INSERT = """
            insert into course (id, name, author)
            values (?, ?, ?)
            """;
    private static final String DELETE = "delete from course where id = ?";
    private static final String SELECT_BY_ID = "select id, name, author from course where id = ?";

    // one row -> one Course, reading each column by its NAME (not by position)
    private static final RowMapper<Course> ROW_MAPPER =
            (rs, rowNum) -> new Course(rs.getLong("id"), rs.getString("name"), rs.getString("author"));

    private final JdbcTemplate jdbcTemplate;

    public CourseJdbcRepository(JdbcTemplate jdbcTemplate) {     // constructor injection
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Course course) {
        jdbcTemplate.update(INSERT, course.getId(), course.getName(), course.getAuthor());
    }

    public void deleteById(long id) {
        jdbcTemplate.update(DELETE, id);
    }

    public Optional<Course> findById(long id) {
        return jdbcTemplate.query(SELECT_BY_ID, ROW_MAPPER, id).stream().findFirst();
    }
}
