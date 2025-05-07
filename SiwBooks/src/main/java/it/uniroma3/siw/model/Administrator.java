package it.uniroma3.siw.model;

import jakarta.persistence.Entity;

@Entity
public class Administrator extends EndUser {

    // Costruttore vuoto (necessario per JPA)
    public Administrator() {
        super();
    }

    // Costruttore con username e password
    public Administrator(String username, String password) {
        super(null, username, password, Role.ADMIN);
    }
}

