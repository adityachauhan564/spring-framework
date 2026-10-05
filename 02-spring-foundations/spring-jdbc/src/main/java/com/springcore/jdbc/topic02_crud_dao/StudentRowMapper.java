package com.springcore.jdbc.topic02_crud_dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/*
 * Turns ONE row of the result into ONE Student. JdbcTemplate calls it once for every row.
 * Read columns by NAME, not by position (like rs.getInt(1)).
 * If someone changes the column order in the SELECT, reading by position would
 * silently put the wrong value in the wrong field - with no error at all.
 */
public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Student(rs.getInt("id"), rs.getString("name"), rs.getString("city"));
    }
}
