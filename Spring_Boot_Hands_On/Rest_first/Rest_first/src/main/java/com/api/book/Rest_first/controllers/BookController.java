package com.api.book.Rest_first.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.Rest_first.entities.Book;
import com.api.book.Rest_first.services.BookService;

//@Controller
@RestController
public class BookController {
	
	//@RequestMapping(value="/books",method=RequestMethod.GET)
	//@ResponseBody
	
	@Autowired
	private BookService bookService;
	
	
	@GetMapping("/books")
	public List<Book> getBooks() {
		/*
		 * Book book=new Book(); book.setId(10); book.setTitle("October Junction");
		 * book.setAuthor("Divya Prakash Dubay");
		 */
		
		return this.bookService.getAllBooks();
	}
	//@GetMapping("/books/{id}")
	/*
	 * public Book getBook(@PathVariable("id")int id) {
	 * 
	 * 
	 * return bookService.getBookById(id); }
	 */
	
	@PostMapping("/books")
	public Book addBook(@RequestBody Book book) {
		
		Book b=this.bookService.addBook(book);
		System.out.println(book);
		return b;
	}
	
	

}

