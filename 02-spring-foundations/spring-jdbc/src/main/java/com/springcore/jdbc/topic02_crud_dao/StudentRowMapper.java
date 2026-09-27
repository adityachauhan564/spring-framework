package com.springcore.jdbc.topic02_crud_dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/*
 * Turns ONE row of the ResultSet into ONE Student. JdbcTemplate calls it for every row.
 * Read columns by NAME, not by position (rs.getInt(1)): a reordered SELECT would
 * silently put the wrong value in the wrong field.
 */
public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Student(rs.getInt("id"), rs.getString("name"), rs.getString("city"));
    }
}
