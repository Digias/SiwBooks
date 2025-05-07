package it.uniroma3.siw.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class RegisteredUser extends EndUser {

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    // Costruttore vuoto (necessario per JPA)
    public RegisteredUser() {
        super();
    }

    // Costruttore solo con username e password
    public RegisteredUser(String username, String password) {
        super(null, username, password, Role.REGISTERED);
    }
    
}
