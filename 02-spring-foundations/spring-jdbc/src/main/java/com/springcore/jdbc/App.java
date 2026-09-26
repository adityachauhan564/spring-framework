package com.springcore.jdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.springcore.jdbc.dao.StudentDao;
import com.springcore.jdbc.entities.Student;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("My Program Started .......................");

		// spring jdbc=> jdbcTemplet

		ApplicationContext context = new AnnotationConfigApplicationContext(jdbcconfig.class);

		//JdbcTemplate template = context.getBean("jdbcTemplet", JdbcTemplate.class);

		// insert query

//        String query="insert into student (id,name,city) values(?,?,?)";
//        
//        
//        int result = template.update(query,10," Khushi. Chauhan","Lucknow");
//        
//        System.out.println("Number of Record Inserted ..."+result);

		StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
		// insert

//		 Student student=new Student();
//		 student.setId(777); 
//		 student.setName("Roshan Chauhan"); 
//		  student.setCity("Lucknow && Dehardun"); 
//		  int result =studentDao.insert(student); 
//		  
//		  System.out.println("Student Added ........"+result); 
		 
		 // update
		 // Student student=new Student();
//        
//        student.setId(777);
//        student.setName("Aditya Chauhan");
//        student.setCity("Lucknow");
//        
//        int result = studentDao.change(student);
//        
//        System.out.println("Student Updated ......................"+result);

		// delete

//        int delete = studentDao.delete(777);
//        
//        System.out.println("Deleted ............."+delete);

//        Student student = studentDao.getStudent(10);
//        System.out.println(student);

		List<Student> students = studentDao.getAllStudents();

		for (Student s : students) {

			System.out.println(s);
		}

	}
}
