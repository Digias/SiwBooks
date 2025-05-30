package it.uniroma3.siw.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
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
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
public class AuthorController {

	@Autowired private AuthorService authorService;
	@Autowired private BookService bookService;

	@GetMapping("/author")
	public String showAuthors(Model model) {
		model.addAttribute("authors", this.authorService.getAllAuthors());
		return "authors.html";
	}

	@GetMapping("/author/{id}")
	public String showAuthors(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		Author author = this.authorService.getAuthorById(id);
		model.addAttribute("author", author);
		model.addAttribute("books", this.authorService.findBooksByAuthorId(id));
		model.addAttribute("photo", author.getPhoto());

		//URL della pagina precedente
		// Validazione del referer
		String backUrl = validateReferer(request.getHeader("Referer"));
		model.addAttribute("backUrl", backUrl);

		return "author.html";
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
					return "/author";
				}
			} catch (Exception e) {
				return "/author"; // Se ci sono errori nella verifica, fallback a /book
			}
		}
		return "/author"; // Se referer è null, fallback a /book
	}

	@GetMapping("/admin/formAddAuthor")
	public String formAddAuthor(Model model) {
		model.addAttribute("books", this.bookService.getAllBooks());
		model.addAttribute("newAuthor", new Author());
		model.addAttribute("selectedBookIds", null);

		return "/admin/formAddAuthor.html";
	}

	@Transactional
	@PostMapping("/admin/addAuthor")
	public String addAutor(@Valid @ModelAttribute("newAuthor") Author newAuthor,
			BindingResult bindingResult,
			@RequestParam("photoFile") MultipartFile photoFile,
			@RequestParam(name = "selectedBooks", required = false) Set<Long> selectedBookIds,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("books", bookService.getAllBooks());
			// Mantieni gli autori selezionati in caso di errori
			model.addAttribute("selectedBookIds", selectedBookIds);
			return "admin/formAddAuthor.html";
		}

		try {
			// Gestione immagine solo se presente
			if (!photoFile.isEmpty()) {
				Image photoImage = new Image();
				photoImage.setName(photoFile.getOriginalFilename());
				photoImage.setData(photoFile.getBytes());
				newAuthor.setPhoto(photoImage);
			}
		} catch (IOException e) {
			model.addAttribute("errorMessage", "Errore nel caricamento dell'immagine");
			model.addAttribute("books", bookService.getAllBooks());
			model.addAttribute("selectedBookIds", selectedBookIds);
			return "admin/formAddAuthor.html";
		}

		// Aggiunta libri
		if (selectedBookIds != null && !selectedBookIds.isEmpty()) {
			Set<Book> selectedBooks = this.bookService.getBooksByIds(selectedBookIds);
			newAuthor.setBooks(selectedBooks);

			// Aggiorna bidirezionalmente i libri
			selectedBooks.forEach(book -> book.getAuthors().add(newAuthor));
		}

		this.authorService.save(newAuthor);
		return "redirect:/author/" + newAuthor.getId();
	}
}
