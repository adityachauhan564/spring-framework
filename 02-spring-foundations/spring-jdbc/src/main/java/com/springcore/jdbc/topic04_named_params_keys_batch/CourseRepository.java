package com.springcore.jdbc.topic04_named_params_keys_batch;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public CourseRepository(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    // 1. named parameters (:title) instead of ? - readable, and order no longer matters
    // 2. KeyHolder receives the id the database generated (AUTO_INCREMENT)
    public Course save(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", course.title())
                .addValue("fee", course.fee());
        jdbc.update("insert into course (title, fee) values (:title, :fee)", params, keyHolder, new String[] {"id"});
        return new Course(keyHolder.getKey().intValue(), course.title(), course.fee());
    }

    // 3. batch: one round trip to the database for many rows. Named parameters are read
    //    from the record's accessors (title(), fee()).
    public int saveAll(List<Course> courses) {
        int[] counts = jdbc.batchUpdate("insert into course (title, fee) values (:title, :fee)",
                SqlParameterSourceUtils.createBatch(courses));
        return Arrays.stream(counts).sum();
    }

    public List<Course> findByFeeBetween(int min, int max) {
        return jdbc.query("select id, title, fee from course where fee between :min and :max order by fee",
                Map.of("min", min, "max", max),
                new DataClassRowMapper<>(Course.class));
    }
}
