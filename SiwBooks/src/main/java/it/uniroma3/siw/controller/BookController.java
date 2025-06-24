package it.uniroma3.siw.controller;

import java.io.IOException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.ImageService;
import it.uniroma3.siw.service.ReviewService;
import it.uniroma3.siw.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
public class BookController {

	@Autowired private BookService bookService;
	@Autowired private AuthorService authorService;
	@Autowired private ReviewService reviewService;
	@Autowired private CredentialsService credentialsService;
	@Autowired private SecurityUtils securityUtils;
	@Autowired private ImageService imageService;

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
	public String getBook(@PathVariable("bookId") Long bookId, 
			@RequestParam(name = "from", defaultValue = "/book") String from, 
			Model model) {
		Book book = this.bookService.getBookbyId(bookId);
		model.addAttribute("book", book);
		model.addAttribute("authors", this.bookService.findAuthorsByBookId(bookId));
		model.addAttribute("cover", book.getCover());
		model.addAttribute("newReview", new Review());
		model.addAttribute("lastReview", this.reviewService.getLast5Review(book));
		model.addAttribute("backUrl", from);

		return "book.html";
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
    			EDIT BOOK
	 */

	@GetMapping("/admin/edit/book/{id}")
	public String formEditBook(@PathVariable("id") Long id, Model model) {
		Book book = this.bookService.getBookbyId(id);
		model.addAttribute("book", book);
		model.addAttribute("cover", book.getCover());

		Iterable<Author> bookAuthors = this.bookService.findAuthorsByBookId(id);
		model.addAttribute("bookAuthors", bookAuthors);
		model.addAttribute("otherAuthors", this.authorService.findAllExcludingAuthors((List<Author>) bookAuthors));

		return "admin/formEditBook.html";
	}

	@PostMapping("/admin/editBook/{id}")
	public String editBook(@PathVariable("id") Long id, 
			@Valid @ModelAttribute Book book,
			BindingResult bindingResult,
			@RequestParam(value = "selectedAuthors", required = false) Set<Long> selectedAuthorIds,
			@RequestParam(value = "unSelectedAuthors", required = false) Set<Long> unSelectedAuthorIds,
			@RequestParam(value = "coverFile", required = false) MultipartFile coverFile,
			Model model,
			RedirectAttributes redirectAttributes) {

		// Se ci sono errori di validazione, ritorna al form
		if (bindingResult.hasErrors()) {
			// Ricarica i dati necessari per il form
			Iterable<Author> bookAuthors = this.bookService.findAuthorsByBookId(id);
			model.addAttribute("bookAuthors", bookAuthors);
			model.addAttribute("otherAuthors", this.authorService.findAllExcludingAuthors((List<Author>) bookAuthors));
			model.addAttribute("cover", book.getCover());
			model.addAttribute("selectedAuthorIds", selectedAuthorIds);
			model.addAttribute("unSelectedAuthorIds", unSelectedAuthorIds);
			return "admin/formEditBook.html";
		}

		try {
			// Recupera il libro esistente dal database
			Book existingBook = this.bookService.getBookbyId(id);

			// Aggiorna i campi del libro
			existingBook.setTitle(book.getTitle());
			existingBook.setYearOfPublication(book.getYearOfPublication());

			// Gestione della copertina
			if (coverFile != null && !coverFile.isEmpty()) {
				// Se c'è una nuova copertina, gestiscila
				// Elimina la vecchia copertina se esiste
				if (existingBook.getCover() != null) {
					this.imageService.deleteImage(existingBook.getCover().getId());
				}

				// Salva la nuova copertina
				Image newCover = this.imageService.save(coverFile);
				existingBook.setCover(newCover);
			}

			Set<Author> updateAuthor = this.bookService.getAuthorsByBookId(id);

			// Gestione autori da rimuovere
			if (unSelectedAuthorIds != null && !unSelectedAuthorIds.isEmpty()) {
				Set<Author> authorsToRemove = this.authorService.getAuthorsByIds(unSelectedAuthorIds);
				updateAuthor.removeAll(authorsToRemove);
			}

			// Aggiunta autori
			if (selectedAuthorIds != null && !selectedAuthorIds.isEmpty()) {
				Set<Author> authorsToAdd = this.authorService.getAuthorsByIds(selectedAuthorIds);
				updateAuthor.addAll(authorsToAdd);
			}

			existingBook.setAuthors(updateAuthor);

			// Salva il libro aggiornato
			this.bookService.save(existingBook);

			redirectAttributes.addFlashAttribute("successMessage", "Libro aggiornato con successo!");
			return "redirect:/book/" + existingBook.getId();

		} catch (Exception e) {
			System.out.println("=========================================================================================================================================================================================");
			System.out.println("Error: " + e.getMessage());
			System.out.println("=========================================================================================================================================================================================");

			redirectAttributes.addFlashAttribute("errorMessage", "Errore durante l'aggiornamento del libro: " + e.getMessage());
			return "redirect:/admin/edit/book/" + id;
		}
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