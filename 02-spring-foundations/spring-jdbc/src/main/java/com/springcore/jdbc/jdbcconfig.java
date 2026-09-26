package com.springcore.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.springcore.jdbc.dao.StudentDao;
import com.springcore.jdbc.dao.StudentDaoImpl;

@Configuration
@ComponentScan(basePackages = {"com.springcore.jdbc.dao"})
public class jdbcconfig {
	@Bean("ds")
	public DriverManagerDataSource getDataSource() {
		
		DriverManagerDataSource ds=new DriverManagerDataSource();
	    ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
	    ds.setUrl("jdbc:mysql://localhost:3306/springjdbc");
	    ds.setUsername(System.getenv().getOrDefault("DB_USERNAME", "root"));
	    ds.setPassword(System.getenv("DB_PASSWORD")); // set DB_PASSWORD env var, never commit it
	    
	    return ds;
	}
	@Bean("jdbcTemplate")
	public JdbcTemplate getTemplate() {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate;
		
	}
	// Autowired done so there is no need of it.
//	@Bean("studentDao") 
//	public StudentDao getStudentDao() {
//		
//		StudentDaoImpl studentDao = new StudentDaoImpl();
//		studentDao.setJdbcTemplet(getTemplate());
//		
//		return studentDao;
//		
//		
//	}
	
}