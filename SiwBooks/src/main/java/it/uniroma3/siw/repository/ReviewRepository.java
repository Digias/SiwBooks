package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {
	
	@Query("SELECT r FROM Review r WHERE r.book = :book ORDER BY r.id DESC LIMIT 5")
    List<Review> findLast5ReviewsForBook(@Param("book") Book book);
	
	@Query("SELECT r FROM Review r WHERE r.book.id = :id")
    List<Review> findReviewsForBook(@Param("id") Long id);

}
