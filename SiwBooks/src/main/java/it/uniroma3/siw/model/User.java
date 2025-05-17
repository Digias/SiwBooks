package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank
    private String name;
	
	@NotBlank
    private String surname;

    @NotBlank
    private String email;
    
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return name;
	}

	public void setUsername(String username) {
		this.name = username;
	}

	public String getPassword() {
		return email;
	}

	public void setPassword(String password) {
		this.email = password;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email, reviews, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id) && Objects.equals(email, other.email)
				&& Objects.equals(reviews, other.reviews) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + name + ", password=" + email + ", reviews=" + reviews + "]";
	}
	
}

