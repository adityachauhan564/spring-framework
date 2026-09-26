package com.api.book.Rest_first.dao;


import org.springframework.data.repository.CrudRepository;

import com.api.book.Rest_first.entities.Book;

public interface BookRepository extends CrudRepository<Book,Integer> {
	
	public Book  findById(int id);

}
