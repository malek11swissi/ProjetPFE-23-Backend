package com.pack.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ticket {
	long id;
	private Typetoken typetoken;
	private User user;
	long numeroSerie;
	String numCompteur;

	// private String typetoken;

	public Ticket() {
	}

	
	public Ticket(Typetoken typetoken, User user, long numeroSerie, String numCompteur) {
		super();
		this.typetoken = typetoken;
		this.user = user;
		this.numeroSerie = numeroSerie;
		this.numCompteur = numCompteur;
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


	public long getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(long numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String getNumCompteur() {
		return numCompteur;
	}


	public void setNumCompteur(String numCompteur) {
		this.numCompteur = numCompteur;
	}


	@Override
	public String toString() {
		return "Ticket [id=" + id + ", typetoken=" + typetoken + ", user=" + user + ", numeroSerie=" + numeroSerie
				+ ", numCompteur=" + numCompteur + "]";
	}
	

}
