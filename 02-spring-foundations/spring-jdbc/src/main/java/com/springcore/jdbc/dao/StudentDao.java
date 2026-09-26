package com.springcore.jdbc.dao;

import java.util.List;

import com.springcore.jdbc.entities.Student;

//Interface have abstract Method(Without body)
public interface StudentDao {
	
	public int insert(Student student); 
	public int change(Student student);
	public int delete(int studentID);
	public Student getStudent(int studentID); //Row Mapper 
	
	public List<Student> getAllStudents();
	
	
	
	
	

}
