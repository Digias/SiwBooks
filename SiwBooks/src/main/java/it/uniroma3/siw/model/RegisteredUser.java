package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class RegisteredUser extends User {

	@OneToMany(mappedBy = "user")
    private List<Review> reviews;

    // Costruttore vuoto (necessario per JPA)
    public RegisteredUser() {
        super();
    }

    // Costruttore solo con username e password
    public RegisteredUser(String username, String password) {
        super(null, username, password, Role.REGISTERED);
    }

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(reviews);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisteredUser other = (RegisteredUser) obj;
		return Objects.equals(reviews, other.reviews);
	}

	@Override
	public String toString() {
		return "RegisteredUser [reviews=" + reviews + "]";
	}
    
}
