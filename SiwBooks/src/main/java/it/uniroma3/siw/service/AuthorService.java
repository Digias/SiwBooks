package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.repository.AuthorRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthorService {
	
	@Autowired private AuthorRepository authorRepository;

	public Iterable<Author> getAllAuthors() {
		return this.authorRepository.findAll();
	}

	public Object findByNameOrSurnameContainingIgnoreCase(String query) {
		// TODO Auto-generated method stub
		return this.authorRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(query, query);
	}

	public Author getAuthorById(Long id) {
		return this.authorRepository.findById(id).orElse(null);
	}
	
	
}
