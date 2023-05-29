package com.pack.models;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "telephone") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	/*@Size(max = 50)
	@Email*/
	@Size(min=8,max = 8)
	private String telephone;

	@NotBlank
	@Size(max = 120)
	private String password;


	private boolean active ;

	@Enumerated(EnumType.STRING)
	private ERole role;

	private String libelle;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "id_gouvernorat")
	private Gouvernorat gouvernorat;


	public User() {
	}

	public User(String username, String telephone, String password) {
		this.username = username;
		this.telephone = telephone;
		this.password = password;
	}

	public User(String username, String telephone, String password, String libelle, Gouvernorat gouvernorat) {
		this.username = username;
		this.telephone = telephone;
		this.password = password;
		this.libelle = libelle;
		this.gouvernorat = gouvernorat;
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

	

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Gouvernorat getGouvernorat() {
		return gouvernorat;
	}

	public void setGouvernorat(Gouvernorat gouvernorat) {
		this.gouvernorat = gouvernorat;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", telephone=" + telephone + ", password=" + password
				+ ", active=" + active + ", role=" + role + ", libelle=" + libelle + ", gouvernorat=" + gouvernorat
				+ "]";
	}

	
	

	
	
	
}
