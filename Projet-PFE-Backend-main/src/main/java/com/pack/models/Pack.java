package com.pack.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pack {
	long id;
	private User user;
	private Typetoken typetoken;
	private int nombre;

	public Pack() {

	}

	public Pack(User user, Typetoken typetoken, int nombre) {
		super();
		this.user = user;
		this.typetoken = typetoken;
		this.nombre = nombre;
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
	@JoinColumn(name = "id_user", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return  "Pack [id=" + id + ", user=" + user + ", typetoken=" + typetoken
		+ ", nombre=" + nombre + "]";
	}

}


	





	


	



	
	


