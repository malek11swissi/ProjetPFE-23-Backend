package com.pack.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Profileform {
	private String username;
	private String telephone;
	private String libelle;
	private Gouvernorat gouvernorat;

	public Profileform() {

	}


	


	


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
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








	public String getUsername() {
		return username;
	}








	public void setUsername(String username) {
		this.username = username;
	}


	

}
