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
public class Commande {
	long id;
	private User user;
	private Panier panier;
	private String date;
	//private String active;

	public Commande() {

	}

	public Commande(User user, Panier panier, String date) {
		super();
		this.user = user;
		this.panier = panier;
		this.date = date;
	}

	/*
	 * public Commande(User user, Panier panier, Date date, String active) {
	 * super(); this.user = user; this.panier = panier; this.date = date;
	 * this.active = active; }
	 */

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
	@JoinColumn(name = "id_panier", nullable = false)

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/*
	 * public String getActive() { return active; }
	 * 
	 * public void setActive(String active) { this.active = active; }
	 */

	@Override
	public String toString() {
		return "Commande [id=" + id + ", user=" + user + ", panier=" + panier + ", date=" + date + "]";
	}

}
