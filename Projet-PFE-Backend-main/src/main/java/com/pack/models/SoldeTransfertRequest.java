package com.pack.models;


public class SoldeTransfertRequest {
    private String telephone;
	private double somme;
    public String getTelephone() {
        return telephone;
    }
    public double getSomme() {
        return somme;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setSomme(double somme) {
        this.somme = somme;
    }

    
}
