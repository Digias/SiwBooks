package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class ReviewService {

	@Autowired private ReviewRepository reviewRepository;

	public void save(@Valid Review review) {
		this.reviewRepository.save(review);
	}

	public List<Review> getLast5Review(Book book) {
	    return reviewRepository.findLast5ReviewsForBook(book);
	}

	public Iterable<Review> getReviewOfBook(Long id) {
		return this.reviewRepository.findReviewsForBook(id);
	}
}
