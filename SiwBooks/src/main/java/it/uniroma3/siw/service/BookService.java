package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.repository.BookRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookService {

	@Autowired private BookRepository bookRepository;

	public Iterable<Book> getAllBooks() {
		return this.bookRepository.findAll();
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
	
	public List<Book> findByTitleContainingIgnoreCase(String query) {
	    return bookRepository.findByTitleContainingIgnoreCase(query);
	}
	
	public List<Book> findByTitleContainingIgnoreCaseAndMinRating(String query, int minRating) {
	    return this.bookRepository.findByTitleContainingIgnoreCaseAndMinRating(query, minRating);
	}

}
