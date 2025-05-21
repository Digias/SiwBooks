package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.utils.SecurityUtils;

@Controller
public class BookController {

    @Autowired private BookService bookService;
    @Autowired private AuthorService authorService;
    @Autowired private CredentialsService credentialsService;
    @Autowired private SecurityUtils securityUtils;

    @GetMapping("/book")
    public String showBooks(Model model) {
        model.addAttribute("books", this.bookService.getAllBooks());
        return "books.html";
    }

    @GetMapping("/book/{bookId}")
    public String getBook(@PathVariable("bookId") Long bookId, Model model) {
        model.addAttribute("book", this.bookService.getBookbyId(bookId));
        model.addAttribute("authors", this.bookService.findAuthorsByBookId(bookId));
        return "book.html";
    }

    @GetMapping("/registered/bestRating")
    public String showBestRating(Model model) {
        if (!securityUtils.isAuthenticated()) {
            return "login";
        }

        if (securityUtils.hasRegisteredOrAdminAccess(credentialsService)) {
            model.addAttribute("books", this.bookService.findTop10Books());
            return "bestRatingBooks.html";
        }

        return "login.html";
    }

    @GetMapping("/registered/search")
    public String showSearch(@RequestParam(name = "query", required = false) String query,
                              @RequestParam(name = "searchInAuthors", required = false) String searchInAuthors,
                              @RequestParam(name = "searchInBooks", required = false) String searchInBooks,
                              Model model) {

        if (!securityUtils.isAuthenticated()) {
            return "login";
        }

        if (!securityUtils.hasRegisteredOrAdminAccess(credentialsService)) {
            return "index.html";
        }

        if (query == null || query.trim().isEmpty()) {
            model.addAttribute("authors", this.authorService.getAllAuthors());
            model.addAttribute("books", this.bookService.getAllBooks());
            return "search.html";
        }

        boolean inAuthors = (searchInAuthors != null);
        boolean inBooks = (searchInBooks != null);

        // Se nessuna checkbox selezionata, cerca in entrambi
        if (!inAuthors && !inBooks) {
            inAuthors = true;
            inBooks = true;
        }

        if (inAuthors) {
            model.addAttribute("authors", authorService.findByNameOrSurnameContainingIgnoreCase(query));
        } else {
            model.addAttribute("authors", List.of());
        }

        if (inBooks) {
            model.addAttribute("books", bookService.findByTitleContainingIgnoreCase(query));
        } else {
            model.addAttribute("books", List.of());
        }

        return "search.html";
    }
}
