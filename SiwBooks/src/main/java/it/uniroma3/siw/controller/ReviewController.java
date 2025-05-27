package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.ReviewService;
import it.uniroma3.siw.utils.SecurityUtils;
import jakarta.validation.Valid;

@Controller
public class ReviewController {

	@Autowired private ReviewService reviewService;
	@Autowired private BookService bookService;
	@Autowired private SecurityUtils securityUtils;
	@Autowired private CredentialsService credentialsService;
	
	@PostMapping("/registered/addReview")
	public String addReview(
			@Valid @ModelAttribute("newReview") Review review,
			BindingResult bindingResult,
			@RequestParam("bookId") Long bookId,  // Ricevi l'ID del libro
			Model model) {

		if(!this.securityUtils.isAuthenticated()) {
			bindingResult.addError(null);
		}
		
		if(!this.securityUtils.hasRegisteredOrAdminAccess(this.credentialsService)) {
			bindingResult.addError(null);
		}
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("book", bookService.getBookbyId(bookId)); // Ricarica il libro
			return "book-details";
		}

		// Recupera il libro dal database
		Book book = bookService.getBookbyId(bookId);
		User user = this.securityUtils.getCurrentCredentials(credentialsService).getUser();

		review.setUser(user);
		review.setBook(book);

		reviewService.save(review);

		return "redirect:/book/" + bookId;
	}

}
