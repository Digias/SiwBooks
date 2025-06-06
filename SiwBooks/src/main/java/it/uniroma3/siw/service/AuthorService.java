package it.uniroma3.siw.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class AuthorService {

	@Autowired private AuthorRepository authorRepository;

	public Iterable<Author> getAllAuthors() {
		//return this.authorRepository.findAll();
		return this.authorRepository.findAllByOrderBySurnameAscNameAsc();
	}

	public Object findByNameOrSurname(String query) {
		return this.authorRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(query, query);
	}

	public Author getAuthorById(Long id) {
		return this.authorRepository.findById(id).orElse(null);
	}

	public Iterable<Book> findBooksByAuthorId(Long authorId) {
		return this.authorRepository.findBooksByAuthorId(authorId);
	}

	public Set<Author> getAuthorsByIds(Set<Long> selectedAuthorIds) {
		Iterable<Author> authorsIterable = authorRepository.findAllById(selectedAuthorIds);
		Set<Author> authorsSet = new HashSet<>();
		authorsIterable.forEach(authorsSet::add);
		return authorsSet;
	}

	public void save(@Valid Author newAuthor) {
		this.authorRepository.save(newAuthor);
	}
	
	public Iterable<Author> findAllExcludingAuthors(List<Author> authors){
		return this.authorRepository.findAllExcludingAuthors(authors);
	}

	public void saveAll(Set<Author> authors) {
		this.authorRepository.saveAll(authors);		
	}

}
