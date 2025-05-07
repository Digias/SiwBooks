package it.uniroma3.siw.model;

import jakarta.persistence.*;

@Entity
public class Review {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
}
