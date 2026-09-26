package com.spring.orm.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.spring.orm.entities.Student;

public class StudentDao {
	private HibernateTemplate hibernateTemplate;

	// setter injection: wire it in config.xml with <property name="hibernateTemplate" ref="..."/>
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	//save student
	public int insert(Student student) {
		
		//insert
		int i=(int)this.hibernateTemplate.save(student);
		return i;
		
	}

}
