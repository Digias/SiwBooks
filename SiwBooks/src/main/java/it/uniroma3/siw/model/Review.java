package it.uniroma3.siw.model;

import java.util.Objects;

import it.uniroma3.siw.validation.ValidRatingReview;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Review {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String title;
	
	@ValidRatingReview		//custom validation (1-5)
	private int rating;
	
	private String text;
	
	@ManyToOne
	private Book book;
	
	@ManyToOne
	private RegisteredUser user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public RegisteredUser getUser() {
		return user;
	}

	public void setUser(RegisteredUser user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(book, id, rating, text, title, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(book, other.book) && Objects.equals(id, other.id) && rating == other.rating
				&& Objects.equals(text, other.text) && Objects.equals(title, other.title)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", title=" + title + ", rating=" + rating + ", text=" + text + ", book=" + book
				+ ", user=" + user + "]";
	}
	
	
	
}
