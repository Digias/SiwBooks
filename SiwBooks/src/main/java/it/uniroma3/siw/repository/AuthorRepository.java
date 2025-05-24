package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

	Optional<Author> findByName(String authorName);

	List<Author> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name, String surname);
	
	// Ordina per cognome (ASC) e poi nome (ASC)
    List<Author> findAllByOrderBySurnameAscNameAsc();
}
