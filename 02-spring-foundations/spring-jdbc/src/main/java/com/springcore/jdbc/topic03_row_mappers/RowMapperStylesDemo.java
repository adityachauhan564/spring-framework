package com.springcore.jdbc.topic03_row_mappers;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.springcore.jdbc.topic01_datasource_and_jdbctemplate.JdbcConfig;
import com.springcore.jdbc.topic02_crud_dao.Student;
import com.springcore.jdbc.topic02_crud_dao.StudentRowMapper;

/*
 * Topic    : Row mappers - turning rows into objects
 * Key idea : the same query, mapped four ways. All give the same Students:
 *              1. a RowMapper class          - reusable, testable (topic02)
 *              2. a lambda                   - RowMapper is a functional interface
 *              3. BeanPropertyRowMapper      - maps columns to setters by name (name -> setName)
 *              4. queryForList               - no class at all: a Map per row
 * Run      : ./mvnw -q -pl spring-jdbc compile exec:java -Dexec.mainClass=com.springcore.jdbc.topic03_row_mappers.RowMapperStylesDemo
 * Try this : alias a column ("select name as full_name") - which mappers still work?
 */
public class RowMapperStylesDemo {

    private static final String SQL = "select id, name, city from student order by id";

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(JdbcConfig.class)) {
            JdbcTemplate jdbc = context.getBean(JdbcTemplate.class);
            jdbc.update("insert into student (id, name, city) values (?, ?, ?)", 1, "Asha", "Pune");
            jdbc.update("insert into student (id, name, city) values (?, ?, ?)", 2, "Ravi", "Delhi");

            List<Student> byClass = jdbc.query(SQL, new StudentRowMapper());

            RowMapper<Student> lambda = (rs, rowNum) -> new Student(rs.getInt("id"), rs.getString("name"), rs.getString("city"));
            List<Student> byLambda = jdbc.query(SQL, lambda);

            List<Student> byBeanProperty = jdbc.query(SQL, new BeanPropertyRowMapper<>(Student.class));

            List<Map<String, Object>> asMaps = jdbc.queryForList(SQL);

            System.out.println("1. RowMapper class:     " + byClass);
            System.out.println("2. lambda:              " + byLambda);
            System.out.println("3. BeanPropertyRowMapper: " + byBeanProperty);
            System.out.println("4. queryForList (maps): " + asMaps);
            System.out.println("same result from 1-3? " + (byClass.toString().equals(byLambda.toString())
                    && byLambda.toString().equals(byBeanProperty.toString())));
        }
    }
}
