package com.udemy.web.services.restful_web_services.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
    // JPA/Hibernate ->Database
	
	//UserDao Service > Static List
	
	private static List<User> users =new ArrayList<>();
	
	private static int usersCount=0;
	
	static {
		
		users.add(new User(++usersCount,"Raman ", LocalDate.now().minusYears(29)));
		users.add(new User(++usersCount,"Padan ", LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount,"Rajan ", LocalDate.now().minusYears(21)));
		
		
	}
	
	//public List<User> findAll() {
	
	public List<User> findAll() {
		
		return users;
	}
	
	//public User save (User user) {
	
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	
	//public User findOne (int id) {
	
	public User findOne(int id) {
		
		Predicate<? super User> predicate=user->user.getId().equals(id);
		
		return users.stream().filter(predicate).findFirst().orElse(null);
	} 
	
	
	
}

