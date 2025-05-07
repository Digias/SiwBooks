package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Image {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Author author;
	
	@ManyToOne
	private Book book;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, book, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return Objects.equals(author, other.author) && Objects.equals(book, other.book) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", author=" + author + ", book=" + book + "]";
	}
	
	
}
