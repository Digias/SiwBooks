package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BookController {

	@Autowired private BookService bookService;
	@Autowired private AuthorService authorService;
	@Autowired private CredentialsService credentialsService;
	@Autowired private SecurityUtils securityUtils;

	@GetMapping("/book")
	public String showBooks(Model model) {
		model.addAttribute("books", this.bookService.getAllBooks());
		return "books.html";
	}

	@GetMapping("/book/{bookId}")
	public String getBook(@PathVariable("bookId") Long bookId, Model model, HttpServletRequest request) {
		Book book = this.bookService.getBookbyId(bookId);
		model.addAttribute("book", book);
		model.addAttribute("authors", this.bookService.findAuthorsByBookId(bookId));
		model.addAttribute("cover", book.getCover());
		model.addAttribute("review", new Review());

		//URL della pagina precedente
		String referer = request.getHeader("Referer");
		model.addAttribute("backUrl", referer != null ? referer : "/book"); // fallback se referer è null
		return "book.html";
	}

	@GetMapping("/registered/bestRating")
	public String showBestRating(Model model) {
		if (!securityUtils.isAuthenticated()) {
			return "login";
		}

		if (securityUtils.hasRegisteredOrAdminAccess(credentialsService)) {
			model.addAttribute("books", this.bookService.findTop10Books());
			return "bestRatingBooks.html";
		}

		return "login.html";
	}

	@GetMapping("/registered/search")
	public String showSearch(@RequestParam(name = "query", required = false) String query,
			@RequestParam(name = "searchInAuthors", required = false) String searchInAuthors,
			@RequestParam(name = "searchInBooks", required = false) String searchInBooks,
			@RequestParam(name = "rating", required = false) Integer rating,
			Model model) {

		if (!securityUtils.isAuthenticated())
			return "login";

		if (!securityUtils.hasRegisteredOrAdminAccess(credentialsService))
			return "index.html";

		if (query == null || query.trim().isEmpty() && rating == null) {
			model.addAttribute("authors", this.authorService.getAllAuthors());
			model.addAttribute("books", this.bookService.getAllBooks());
			return "search.html";
		}

		boolean inAuthors = (searchInAuthors != null);
		boolean inBooks = (searchInBooks != null);
		boolean byRating = (rating != null);

		// Se nessuna checkbox selezionata, cerca in entrambi
		if (!inAuthors && !inBooks) {
			inAuthors = true;
			inBooks = true;
		}

		if (inAuthors)
			model.addAttribute("authors", authorService.findByNameOrSurname(query));
		else
			model.addAttribute("authors", List.of());

		if (inBooks) {
			List<Book> books = this.bookService.findBooksByTitle(query);
			if(byRating) {
				List<Book> ratingBook = this.bookService.findBooksByRating(rating.intValue());
				books.retainAll(ratingBook);
			}

			model.addAttribute("books", books);
		} else {
			model.addAttribute("books", List.of());
		}

		return "search.html";
	}

	@GetMapping("/admin/book")
	public String showBooksAdmin(Model model) {
		//controllo se è autenticato e se ha i permessi admin
		if(!this.securityUtils.isAuthenticated() && !this.securityUtils.isAdmin(credentialsService))
			return "login";
		model.addAttribute("books", this.bookService.getAllBooks());
		return "admin/booksAdmin.html";
	}

	@GetMapping("/admin/book/{bookId}")
	public String getBookAdmin(@PathVariable("bookId") Long bookId, Model model, HttpServletRequest request) {
		//controllo se è autenticato e se ha i permessi admin
		if(!this.securityUtils.isAuthenticated())
			return "login";
		
		if(!this.securityUtils.isAdmin(credentialsService))
			return "login";
		
		Book book = this.bookService.getBookbyId(bookId);
		model.addAttribute("book", book);
		model.addAttribute("authors", this.bookService.findAuthorsByBookId(bookId));
		model.addAttribute("cover", book.getCover());
		model.addAttribute("review", new Review());

		//URL della pagina precedente
		String referer = request.getHeader("Referer");
		model.addAttribute("backUrl", referer != null ? referer : "/book"); // fallback se referer è null
		return "book.html";
	}

	// /admin/author/edit/
}
