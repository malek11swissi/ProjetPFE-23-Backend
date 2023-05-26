
package com.pack.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

public class StatAnnulleToken {
	long id;
	int annee;
	int nb;
	String typeToken;


	public StatAnnulleToken() {
	}


	public StatAnnulleToken(int annee, int nb, String typeToken2) {
		super();
		this.annee = annee;
		this.nb = nb;
		this.typeToken = typeToken2;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getNb() {
		return nb;
	}


	public void setNb(int nb) {
		this.nb = nb;
	}


	public void setTypeToken(String type)
	{
		this.typeToken = type;
	}
	
	public String getTypeToken()
	{
		return typeToken;
	}

	@Override
	public String toString() {
		return "StatistiqueAnnuel Par Type Token [id=" + id + ", annee=" + annee + ", nb=" + nb + "]";
	}
}
