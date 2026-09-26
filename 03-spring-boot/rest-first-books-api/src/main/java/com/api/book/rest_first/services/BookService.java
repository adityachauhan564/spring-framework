package com.api.book.rest_first.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.rest_first.dao.BookRepository;
import com.api.book.rest_first.entities.Book;

@Component
public class BookService {

    //private final dao.BookRepository bookRepository_1;

	@Autowired
	private BookRepository bookRepository;

	
	/*
	 * private static List<Book> list=new ArrayList<>();
	 * 
	 * static {
	 * 
	 * list.add(new Book(11,"Head First Java, The best fundamental book"
	 * ,"Kathy Sierra ,Bert Bates & Trisha Gee ")); list.add(new
	 * Book(12,"Head First Design Pattern","Eric Freeman & 2 ")); }
	 */
	//get all books
	public List<Book> getAllBooks() {
	List<Book> list=(List<Book>)this.bookRepository.findAll();
		return list; 
	}
	
	//get single book by id;
	/*
	 * public Book getBookById(int id) { Book book=null; book
	 * =list.stream().filter(e-> e.getId()==id).findFirst().get(); return book; }
	 */
	//adding the book
	public Book addBook(Book b) {
		// TODO Auto-generated method stub
		Book result=bookRepository.save(b);
		
		return result;
		
	}
	
}
