package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.repository.BookRepository;

public class BookService {

	@Autowired private BookRepository bookRepository;
}
