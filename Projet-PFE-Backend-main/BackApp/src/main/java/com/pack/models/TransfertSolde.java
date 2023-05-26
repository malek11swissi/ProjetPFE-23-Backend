package com.pack.models;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TransfertSolde {
	long id;
	private User user;
	private String telephone;
	private double somme;
	// private String active;

	public TransfertSolde() {

	}

	public TransfertSolde(User user, String telephone, double somme) {
		super();
		this.user = user;
		this.telephone = telephone;
		this.somme = somme;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "id_userfrom", nullable = false)
	 * 
	 * public User getUserfrom() { return userfrom; }
	 * 
	 * public void setUserfrom(User userfrom) { this.userfrom = userfrom; }
	 * 
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "id_userto", nullable = false)
	 * 
	 * public User getUserto() { return userto; }
	 * 
	 * public void setUserto(User userto) { this.userto = userto; }
	 */

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public double getSomme() {
		return somme;
	}

	public void setSomme(double somme) {
		this.somme = somme;
	}

	@Override
	public String toString() {
		return "TransfertSolde [id=" + id + ", user=" + user + ", telephone=" + telephone + ", somme=" + somme + "]";
	}

}
