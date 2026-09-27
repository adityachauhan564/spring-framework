package com.springcore.jdbc.topic02_crud_dao;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/*
 * @Repository = @Component for data-access classes. Spring also uses it to translate
 * database exceptions into DataAccessException.
 * The JdbcTemplate arrives through the constructor (topic06 of spring-core).
 */
@Repository
public class StudentDaoImpl implements StudentDao {

    private static final StudentRowMapper ROW_MAPPER = new StudentRowMapper();

    private final JdbcTemplate jdbcTemplate;

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(Student student) {
        return jdbcTemplate.update("insert into student (id, name, city) values (?, ?, ?)",
                student.getId(), student.getName(), student.getCity());
    }

    @Override
    public int update(Student student) {
        return jdbcTemplate.update("update student set name = ?, city = ? where id = ?",
                student.getName(), student.getCity(), student.getId());
    }

    @Override
    public int delete(int studentId) {
        return jdbcTemplate.update("delete from student where id = ?", studentId);
    }

    @Override
    public Optional<Student> findById(int studentId) {
        // query() returns an empty list when nothing matches; queryForObject() would throw
        // EmptyResultDataAccessException instead
        return jdbcTemplate.query("select id, name, city from student where id = ?", ROW_MAPPER, studentId)
                .stream()
                .findFirst();
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("select id, name, city from student order by id", ROW_MAPPER);
    }
}
