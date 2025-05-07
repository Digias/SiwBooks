package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import it.uniroma3.siw.validation.ValidPublicationYear;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Book {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String title;
	
	@NotNull
    @ValidPublicationYear
	private int yearOfPublication;
	
	@OneToMany(mappedBy = "book")
	private List<Image> illustration;
	
	@ManyToMany
	private Set<Author> authors;
	
	//review da aggiungere list

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

	public int getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(int yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}

	public List<Image> getIllustration() {
		return illustration;
	}

	public void setIllustration(List<Image> illustration) {
		this.illustration = illustration;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authors, id, illustration, title, yearOfPublication);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(authors, other.authors) && Objects.equals(id, other.id)
				&& Objects.equals(illustration, other.illustration) && Objects.equals(title, other.title)
				&& yearOfPublication == other.yearOfPublication;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", yearOfPublication=" + yearOfPublication + ", illustration="
				+ illustration + ", authors=" + authors + "]";
	}
	
	
}
