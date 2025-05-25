package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.ImageService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AuthorController {

	@Autowired private AuthorService authorService;
	
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
	    String referer = request.getHeader("Referer");
	    model.addAttribute("backUrl", referer != null ? referer : "/author"); // fallback se referer è null
	    
		return "author.html";
	}
}
