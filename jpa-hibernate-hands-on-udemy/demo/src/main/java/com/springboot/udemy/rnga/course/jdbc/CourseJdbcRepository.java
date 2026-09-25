package com.springboot.udemy.rnga.course.jdbc;

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
            values (2,'Head first Desing Pattern','Eric Freeman');
			
			""";
	
	public void insert() {
		springJdbcTemplate.update(INSERT_QUERY);
	}
	
}
