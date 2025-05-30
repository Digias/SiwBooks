package it.uniroma3.siw.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.repository.BookRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class BookService {

	@Autowired private BookRepository bookRepository;

	public Iterable<Book> getAllBooks() {
		//return this.bookRepository.findAll();
		return this.bookRepository.findAllByOrderByTitleAsc();
	}
	
	public Book getBookbyId(Long id) {
		return this.bookRepository.findById(id).orElse(null);
	}

	public Iterable<Author> findAuthorsByBookId(Long bookId) {
		return this.bookRepository.findAuthorsByBookId(bookId);
	}

	public Iterable<Book> findTop10Books() {
		return this.bookRepository.findTop10Books();
	}
	
	public List<Book> findBooksByTitle(String query) {
	    return bookRepository.findByTitleContainingIgnoreCase(query);
	}
	
	public List<Book> findBooksByRating(int exactRating) {
	    return this.bookRepository.findBooksByRoundedRating(exactRating);
	}

	public void save(@Valid Book book) {
		this.bookRepository.save(book);
	}

	public Set<Book> getBooksByIds(Set<Long> selectedBookIds) {
		Iterable<Book> booksIterable = bookRepository.findAllById(selectedBookIds);
		Set<Book> booksSet = new HashSet<>();
		booksIterable.forEach(booksSet::add);
		return booksSet;
	}
	
/*
	public List<Book> findByTitleContainingIgnoreCaseAndMinRating(String query, int minRating) {
	    return this.bookRepository.findByTitleContainingIgnoreCaseAndMinRating(query, minRating);
	}
	
	public List<Book> findByTitleAndRating(String query, int rating) {
	    return this.bookRepository.findByTitleAndExactAverageRating(query, rating);
	}
*/
}
