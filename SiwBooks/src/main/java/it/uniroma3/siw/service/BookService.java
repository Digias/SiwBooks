package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.repository.BookRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookService {

	@Autowired private BookRepository bookRepository;
}
