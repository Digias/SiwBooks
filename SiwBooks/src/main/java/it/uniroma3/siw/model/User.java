package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank
    private String username;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
	
	// Costruttore vuoto
    public User() {
    }

    // Costruttore completo
    public User(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, password, role, username);
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
		return Objects.equals(id, other.id) && Objects.equals(password, other.password) && role == other.role
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "EndUser [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}
    
    

}

