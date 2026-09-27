package com.springcore.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.springcore.jdbc.topic01_datasource_and_jdbctemplate.JdbcConfig;
import com.springcore.jdbc.topic04_named_params_keys_batch.Course;
import com.springcore.jdbc.topic04_named_params_keys_batch.CourseRepository;

@SpringJUnitConfig({JdbcConfig.class, CourseRepository.class})
@TestPropertySource(properties = "DB_URL=")
class CourseRepositoryTest {

    @Autowired
    CourseRepository courses;

    @Test
    void saveReturnsTheGeneratedIdAndBatchInsertsEveryRow() {
        Course saved = courses.save(Course.unsaved("Spring", 9000));
        assertNotNull(saved.id());

        int inserted = courses.saveAll(List.of(Course.unsaved("Docker", 3000), Course.unsaved("AWS", 12000)));
        assertEquals(2, inserted);

        List<Course> midRange = courses.findByFeeBetween(2500, 9000);
        assertEquals(List.of("Docker", "Spring"), midRange.stream().map(Course::title).toList());
    }
}
