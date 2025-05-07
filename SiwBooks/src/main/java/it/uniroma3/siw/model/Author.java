package it.uniroma3.siw.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Author {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String surname;
	
	private Date dateOfBirth;
	
	private Date dateOfDeath;
	
	private String nationality;
	
	@OneToOne(mappedBy = "author")
	private Image photo;
	
	@ManyToMany(mappedBy = "authors")
	private List<Book> books;
}
