package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.BookService;

@Controller
public class BookController {

	@Autowired private BookService bookService;
	
	@GetMapping("/book")
	public String showBooks(Model model) {
		model.addAttribute("books", this.bookService.getAllBooks());
		return "books.html";
	}
	
	@GetMapping("/book/{bookId}")
	public String getBook(@PathVariable("bookId") Long bookId, Model model) {
		model.addAttribute("book", this.bookService.getBookbyId(bookId));
		model.addAttribute("authors", this.bookService.findAuthorsByBookId(bookId) );
		return "book.html";
	}
	
	
}
