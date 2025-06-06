package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;

public interface AuthorRepository extends CrudRepository<Author, Long> {

	Optional<Author> findByName(String authorName);

	List<Author> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name, String surname);
	
	// Ordina per cognome (ASC) e poi nome (ASC)
    List<Author> findAllByOrderBySurnameAscNameAsc();

    @Query("SELECT b FROM Author a JOIN a.books b WHERE a.id = :authorId")
    Iterable<Book> findBooksByAuthorId(@Param("authorId") Long authorId);
    
    @Query("SELECT a FROM Author a WHERE a NOT IN :authors")
    Iterable<Author> findAllExcludingAuthors(@Param("authors") List<Author> authors);

}
