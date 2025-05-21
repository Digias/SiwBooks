package it.uniroma3.siw.service;

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
}
