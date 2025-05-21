package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

	@Query("SELECT a FROM Book b JOIN b.authors a WHERE b.id = :bookId")
	Iterable<Author> findAuthorsByBookId(@Param("bookId") Long bookId);

	@Query("SELECT b FROM Book b JOIN b.reviews r GROUP BY b ORDER BY AVG(r.rating) DESC LIMIT 10")
	Iterable<Book> findTop10Books();

	List<Book> findByTitleContainingIgnoreCase(String title);

	@Query("SELECT b FROM Book b LEFT JOIN b.reviews r " +
			"GROUP BY b " +
			"HAVING (:query IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%'))) " +
			"AND (COALESCE(AVG(r.rating), 0) >= :minRating)")
	List<Book> findByTitleContainingIgnoreCaseAndMinRating(@Param("query") String query, @Param("minRating") int minRating);

}
