package com.pack.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Bonus {
	long id;
	private double min;
	private double max;
	private double valeur;

	public Bonus() {

	}

	public Bonus(double min, double max, double valeur) {
		super();
		this.min = min;
		this.max = max;
		this.valeur = valeur;
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
	 * public Boolean getActive() { return active; }
	 * 
	 * public void setActive(Boolean active) { this.active = active; }
	 */

	public double getValeur() {
		return valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "Bonus [id=" + id + ", min=" + min + ", max=" + max + ", valeur=" + valeur + "]";
	}

}
