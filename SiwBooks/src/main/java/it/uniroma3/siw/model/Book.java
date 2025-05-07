package it.uniroma3.siw.model;

import java.util.List;

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
	private List<Author> authors;
	
}
