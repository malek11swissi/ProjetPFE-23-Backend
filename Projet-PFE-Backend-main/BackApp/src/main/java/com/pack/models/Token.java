package com.pack.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Token {
	long id;
	private Compteur compteur;
	private Typetoken typetoken;
	private User user;
	private Boolean active;
	private String numerotoken;

	// private String typetoken;

	public Token() {

	}

	public Token(Compteur compteur, Typetoken typetoken) {
		super();
		this.compteur = compteur;
		this.typetoken = typetoken;
	}

	public Token(Compteur compteur, Typetoken typetoken, User user) {
		super();
		this.compteur = compteur;
		this.typetoken = typetoken;
		this.user = user;
	}
	
	
	
	public Token(Compteur compteur, Typetoken typetoken, User user, Boolean active) {
		super();
		this.compteur = compteur;
		this.typetoken = typetoken;
		this.user = user;
		this.active = active;
	}

	
	
	public Token(Compteur compteur, Typetoken typetoken, User user, Boolean active, String numerotoken) {
		super();
		this.compteur = compteur;
		this.typetoken = typetoken;
		this.user = user;
		this.active = active;
		this.numerotoken = numerotoken;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_typetoken", nullable = false)

	public Typetoken getTypetoken() {
		return typetoken;
	}

	public void setTypetoken(Typetoken typetoken) {
		this.typetoken = typetoken;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_compteur", nullable = false)
	public Compteur getCompteur() {
		return compteur;
	}

	public void setCompteur(Compteur compteur) {
		this.compteur = compteur;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	
	
	public String getNumerotoken() {
		return numerotoken;
	}

	public void setNumerotoken(String numerotoken) {
		this.numerotoken = numerotoken;
	}

	@Override
	public String toString() {
		return "Token [id=" + id + ", compteur=" + compteur + ", typetoken=" + typetoken + ", user=" + user
				+ ", active=" + active + ", numerotoken=" + numerotoken + "]";
	}



}
