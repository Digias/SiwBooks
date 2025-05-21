package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Role;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.CredentialsService;

@Controller
public class BookController {

	@Autowired private BookService bookService;
	@Autowired private CredentialsService credentialsService;
	
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
	
	@GetMapping("/registered/bestRating")
	public String showBestRating(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		else {		
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
			if (credentials.getRole().equals(Role.REGISTERED) || credentials.getRole().equals(Role.ADMIN)) {
				model.addAttribute("books", this.bookService.findTop10Books());
				return "bestRatingBooks.html";
			}
		}
		return "login.html";
	}
	
}
