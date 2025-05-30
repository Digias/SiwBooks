package it.uniroma3.siw.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
public class BookController {

	@Autowired private BookService bookService;
	@Autowired private AuthorService authorService;
	@Autowired private CredentialsService credentialsService;
	@Autowired private SecurityUtils securityUtils;

	/*
				ALL BOOKS
	 */
	
	@GetMapping("/book")
	public String showBooks(Model model) {
		model.addAttribute("books", this.bookService.getAllBooks());
		return "books.html";
	}

	/*
				SPECIFIC BOOK
	 */

	@GetMapping("/book/{bookId}")
	public String getBook(@PathVariable("bookId") Long bookId, Model model, HttpServletRequest request) {
		Book book = this.bookService.getBookbyId(bookId);
		model.addAttribute("book", book);
		model.addAttribute("authors", this.bookService.findAuthorsByBookId(bookId));
		model.addAttribute("cover", book.getCover());
		model.addAttribute("newReview", new Review());

		// Validazione del referer
		String backUrl = validateReferer(request.getHeader("Referer"));
		model.addAttribute("backUrl", backUrl);

		return "book.html";
	}


	/*
				REFERER CONTROL FOR BACK BUTTON
	 */

	private String validateReferer(String referer) {
		if (referer != null) {
			try {
				URL url = new URL(referer);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("HEAD");
				connection.connect();

				int responseCode = connection.getResponseCode();

				// Se la pagina non è accessibile (non 2xx) impostiamo il fallback
				if (responseCode < 200 || responseCode >= 300) {
					return "/book";
				}
			} catch (Exception e) {
				return "/book"; // Se ci sono errori nella verifica, fallback a /book
			}
		}
		return "/book"; // Se referer è null, fallback a /book
	}

	/*
				10 BEST BOOK BY RATING	
	 */
	
	@Transactional
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
	
	/*
				SEARCH BOOK 
	 */

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

	/* 
	  		ADD BOOK
	 */

	@GetMapping("/admin/formAddBook")
	public String formAddBook(Model model) {
		model.addAttribute("authors", this.authorService.getAllAuthors());
		model.addAttribute("newBook", new Book());
		model.addAttribute("selectedAuthorIds", null); // Inizializza a null per evitare errori
		return "admin/formAddBook.html";
	}

	@PostMapping("/admin/addBook")
	public String addBook(@Valid @ModelAttribute("newBook") Book newBook,
			BindingResult bindingResult,
			@RequestParam("coverFile") MultipartFile coverFile,
			@RequestParam(name = "selectedAuthors", required = false) Set<Long> selectedAuthorIds,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("authors", authorService.getAllAuthors());
			// Mantieni gli autori selezionati in caso di errori
			model.addAttribute("selectedAuthorIds", selectedAuthorIds);
			return "admin/formAddBook.html";
		}

		try {
			// Gestione immagine solo se presente
			if (!coverFile.isEmpty()) {
				Image coverImage = new Image();
				coverImage.setName(coverFile.getOriginalFilename());
				coverImage.setData(coverFile.getBytes());
				newBook.setCover(coverImage);
			}
		} catch (IOException e) {
			model.addAttribute("errorMessage", "Errore nel caricamento dell'immagine");
			model.addAttribute("authors", authorService.getAllAuthors());
			model.addAttribute("selectedAuthorIds", selectedAuthorIds);
			return "admin/formAddBook.html";
		}

		// Aggiunta autori
		if (selectedAuthorIds != null && !selectedAuthorIds.isEmpty()) {
			Set<Author> selectedAuthors = authorService.getAuthorsByIds(selectedAuthorIds);
			newBook.setAuthors(selectedAuthors);

			// Aggiorna bidirezionalmente gli autori
			selectedAuthors.forEach(author -> author.getBooks().add(newBook));
		}

		bookService.save(newBook);
		return "redirect:/book/" + newBook.getId();
	}
}