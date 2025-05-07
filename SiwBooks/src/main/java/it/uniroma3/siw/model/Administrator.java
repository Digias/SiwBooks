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

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Administrator []";
	}
    
    
}

