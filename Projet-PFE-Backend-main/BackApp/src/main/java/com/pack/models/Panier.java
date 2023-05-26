package com.pack.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Panier {
	long id;
	private User user;
	private Token token;
	private Boolean active;

	public Panier() {

	}

	public Panier(User user, Token token) {
		super();
		this.user = user;
		this.token = token;
	}

	public Panier(User user, Token token, Boolean active) {
		super();
		this.user = user;
		this.token = token;
		this.active=active;
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
	@JoinColumn(name = "id_token", nullable = false)

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
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
	 * public Boolean getActive() { return active; }
	 * 
	 * public void setActive(Boolean active) { this.active = active; }
	 */

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Panier [id=" + id + ", user=" + user + ", token=" + token + ", active=" + active + "]";
	}



}
