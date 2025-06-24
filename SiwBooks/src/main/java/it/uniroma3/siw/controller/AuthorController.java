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
	@Autowired private ImageService imageService;

	/*
			ALL AUTHORS
	 */
	@GetMapping("/author")
	public String showAuthors(Model model) {
		model.addAttribute("authors", this.authorService.getAllAuthors());
		return "authors.html";
	}

	/*
			SPECIFIC AUTHOR
	 */
	@GetMapping("/author/{id}")
	public String showAuthors(@PathVariable("id") Long id, @RequestParam(name = "from", defaultValue = "/author") String from, Model model, HttpServletRequest request) {
		Author author = this.authorService.getAuthorById(id);
		model.addAttribute("author", author);
		model.addAttribute("books", this.authorService.findBooksByAuthorId(id));
		model.addAttribute("photo", author.getPhoto());
		model.addAttribute("backUrl", from);

		return "author.html";
	}

	/* 
			EDIT AUTHOR
	 */

	@GetMapping("/admin/edit/author/{id}")
	public String formEditBook(@PathVariable("id") Long id, Model model) {
		Author author = this.authorService.getAuthorById(id);
		model.addAttribute("author", author);
		model.addAttribute("photo", author.getPhoto());

		Iterable<Book> authorBooks = this.authorService.findBooksByAuthorId(id);
		model.addAttribute("authorBooks", authorBooks);
		model.addAttribute("otherBooks", this.bookService.findAllExcludingBooks((List<Book>) authorBooks));

		return "admin/formEditAuthor.html";
	}

	@PostMapping("/admin/editAuthor/{id}")
	public String editAuthor(@PathVariable("id") Long id,
			@Valid @ModelAttribute("author") Author author,
			BindingResult bindingResult,
			@RequestParam(value = "selectedBooks", required = false) Set<Long> selectedBookIds,
			@RequestParam(value = "unSelectedBooks", required = false) Set<Long> unSelectedBookIds,
			@RequestParam(value = "photoFile", required = false) MultipartFile photoFile,
			Model model,
			RedirectAttributes redirectAttributes) {

		// Gestione errori di validazione
		if (bindingResult.hasErrors()) {
			// Ricarica i dati necessari per il form
			
			//se date non sono state caricate correttamente
			Author originalAuthor = authorService.getAuthorById(id);
	        if (author.getDateOfBirth() == null) {
	            author.setDateOfBirth(originalAuthor.getDateOfBirth());
	        }
	        if (author.getDateOfDeath() == null) {
	            author.setDateOfDeath(originalAuthor.getDateOfDeath());
	        }
			
	     // Ricarica i dati necessari per il form
	        model.addAttribute("photo", originalAuthor.getPhoto());
	        
	        Iterable<Book> authorBooks = authorService.findBooksByAuthorId(id);
	        model.addAttribute("authorBooks", authorBooks);
	        model.addAttribute("otherBooks", bookService.findAllExcludingBooks((List<Book>) authorBooks));
	        
	        // Mantieni lo stato delle checkbox
	        model.addAttribute("selectedBookIds", selectedBookIds);
	        model.addAttribute("unSelectedBooksIds", unSelectedBookIds);

			return "admin/formEditAuthor.html";
		}

		try {
			// Recupera l'autore esistente
			Author existingAuthor = authorService.getAuthorById(id);

			// Aggiorna i campi dell'autore
	        existingAuthor.setName(author.getName());
	        existingAuthor.setSurname(author.getSurname());
	        existingAuthor.setNationality(author.getNationality());
	        
	        // Usa le date dall'oggetto solo se sono state modificate
	        if (author.getDateOfBirth() != null) {
	            existingAuthor.setDateOfBirth(author.getDateOfBirth());
	        }
	        if (author.getDateOfDeath() != null) {
	            existingAuthor.setDateOfDeath(author.getDateOfDeath());
	        }

			// Gestione foto
			if (photoFile != null && !photoFile.isEmpty()) {
				// Elimina la vecchia foto se esiste
				if (existingAuthor.getPhoto() != null) {
					imageService.deleteImage(existingAuthor.getPhoto().getId());
				}

				// Salva la nuova foto
				Image newPhoto = imageService.save(photoFile);
				existingAuthor.setPhoto(newPhoto);
			}

			// Gestione libri
			Set<Book> updatedBooks = this.authorService.getBooksByAuthorId(id);

			// Rimuovi libri deselezionati
			if (unSelectedBookIds != null && !unSelectedBookIds.isEmpty()) {
				Set<Book> booksToRemove = bookService.getBooksByIds(unSelectedBookIds);
				updatedBooks.removeAll(booksToRemove);

			}

			// Aggiungi libri selezionati
			if (selectedBookIds != null && !selectedBookIds.isEmpty()) {
				Set<Book> booksToAdd = bookService.getBooksByIds(selectedBookIds);
				updatedBooks.addAll(booksToAdd);

			}

			existingAuthor.setBooks(updatedBooks);

			// Salva le modifiche
			authorService.save(existingAuthor);

			redirectAttributes.addFlashAttribute("successMessage", "Autore aggiornato con successo!");
			return "redirect:/author/" + existingAuthor.getId();

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Errore durante l'aggiornamento: " + e.getMessage());
			return "redirect:/admin/edit/author/" + id;
		}
	}

	/*
			ADD AUTHOR
	 */
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
