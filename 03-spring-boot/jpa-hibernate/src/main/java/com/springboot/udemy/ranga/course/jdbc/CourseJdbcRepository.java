package com.springboot.udemy.ranga.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {

	@Autowired
	private JdbcTemplate springJdbcTemplate;
	
	public static String INSERT_QUERY=
			
			"""
			INSERT INTO course (id,name,author) 
            values (2,'Head first Design Pattern','Eric Freeman');
			
			""";
	
	public void insert() {
		springJdbcTemplate.update(INSERT_QUERY);
	}
	
}
