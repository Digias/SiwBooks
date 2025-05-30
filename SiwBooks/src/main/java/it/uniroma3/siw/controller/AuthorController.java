package it.uniroma3.siw.controller;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.ImageService;
import jakarta.servlet.http.HttpServletRequest;

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
		String referer = this.validateReferer(request.getHeader("Referer"));
		model.addAttribute("backUrl", referer != null ? referer : "/author"); // fallback se referer è null

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
		
		model.addAttribute("newAuthor", new Author());
		model.addAttribute("books", this.bookService.getAllBooks());
		model.addAttribute("selectedBookIds", null);
		
		return "/admin/formAddAuthor.html";
	}
}
