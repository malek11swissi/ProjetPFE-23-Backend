package com.pack.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class StatistiqueMensuelDetails {
	long id;
	int annee;
	int nbmois1;
	int nbmois2;
	int nbmois3;
	int nbmois4;
	int nbmois5;
	int nbmois6;
	int nbmois7;
	int nbmois8;
	int nbmois9;
	int nbmois10;
	int nbmois11;
	int nbmois12;
	int total;
	

	
	public StatistiqueMensuelDetails() {
	}

	public StatistiqueMensuelDetails(int annee, int mois,int nb,int total) {
		this.annee=annee;
		this.total=total;
		switch(mois) {
		  case 1:
			  	nbmois1=nb;
			  	break;
		  case 2:
			  	nbmois2=nb;
			  	break;
		  case 3:
			  	nbmois3=nb;
			  	break;
		  case 4:
			  	nbmois4=nb;
			  	break;
		  case 5:
			  	nbmois5=nb;
			  	break;
		  case 6:
			  	nbmois6=nb;
			  	break;
		  case 7:
			  	nbmois7=nb;
			  	break;
		  case 8:
			  	nbmois8=nb;
			  	break;
		  case 9:
			  	nbmois9=nb;
			  	break;
		  case 10:
			  	nbmois10=nb;
			  	break;
		  case 11:
			  	nbmois11=nb;
			  	break;
		  case 12:
			  	nbmois12=nb;
			  	break;
		}
		
	}

	public StatistiqueMensuelDetails(int annee, int nbmois1, int nbmois2, int nbmois3, int nbmois4, int nbmois5,
			int nbmois6, int nbmois7, int nbmois8, int nbmois9, int nbmois10, int nbmois11, int nbmois12,int total) {
		super();
		this.annee = annee;
		this.nbmois1 = nbmois1;
		this.nbmois2 = nbmois2;
		this.nbmois3 = nbmois3;
		this.nbmois4 = nbmois4;
		this.nbmois5 = nbmois5;
		this.nbmois6 = nbmois6;
		this.nbmois7 = nbmois7;
		this.nbmois8 = nbmois8;
		this.nbmois9 = nbmois9;
		this.nbmois10 = nbmois10;
		this.nbmois11 = nbmois11;
		this.nbmois12 = nbmois12;
		this.total=total;
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


	public int getNbmois1() {
		return nbmois1;
	}

	public void setNbmois1(int nbmois1) {
		this.nbmois1 = nbmois1;
	}

	public int getNbmois2() {
		return nbmois2;
	}

	public void setNbmois2(int nbmois2) {
		this.nbmois2 = nbmois2;
	}

	public int getNbmois3() {
		return nbmois3;
	}

	public void setNbmois3(int nbmois3) {
		this.nbmois3 = nbmois3;
	}

	public int getNbmois4() {
		return nbmois4;
	}

	public void setNbmois4(int nbmois4) {
		this.nbmois4 = nbmois4;
	}

	public int getNbmois5() {
		return nbmois5;
	}

	public void setNbmois5(int nbmois5) {
		this.nbmois5 = nbmois5;
	}

	public int getNbmois6() {
		return nbmois6;
	}

	public void setNbmois6(int nbmois6) {
		this.nbmois6 = nbmois6;
	}

	public int getNbmois7() {
		return nbmois7;
	}

	public void setNbmois7(int nbmois7) {
		this.nbmois7 = nbmois7;
	}

	public int getNbmois8() {
		return nbmois8;
	}

	public void setNbmois8(int nbmois8) {
		this.nbmois8 = nbmois8;
	}

	public int getNbmois9() {
		return nbmois9;
	}

	public void setNbmois9(int nbmois9) {
		this.nbmois9 = nbmois9;
	}

	public int getNbmois10() {
		return nbmois10;
	}

	public void setNbmois10(int nbmois10) {
		this.nbmois10 = nbmois10;
	}

	public int getNbmois11() {
		return nbmois11;
	}

	public void setNbmois11(int nbmois11) {
		this.nbmois11 = nbmois11;
	}

	public int getNbmois12() {
		return nbmois12;
	}

	public void setNbmois12(int nbmois12) {
		this.nbmois12 = nbmois12;
	}

	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "StatistiqueMensuelDetails [id=" + id + ", annee=" + annee + ", nbmois1=" + nbmois1 + ", nbmois2="
				+ nbmois2 + ", nbmois3=" + nbmois3 + ", nbmois4=" + nbmois4 + ", nbmois5=" + nbmois5 + ", nbmois6="
				+ nbmois6 + ", nbmois7=" + nbmois7 + ", nbmois8=" + nbmois8 + ", nbmois9=" + nbmois9 + ", nbmois10="
				+ nbmois10 + ", nbmois11=" + nbmois11 + ", nbmois12=" + nbmois12 + ", total=" + total + "]";
	}
}
