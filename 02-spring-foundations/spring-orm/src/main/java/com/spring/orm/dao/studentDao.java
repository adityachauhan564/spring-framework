package com.spring.orm.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.spring.orm.entities.Student;

public class studentDao {
	private HibernateTemplate hibernateTemplate;
	//save student
	public int insert(Student student) {
		
		//insert
		int i=(int)this.hibernateTemplate.save(student);
		return i;
		
	}

}
