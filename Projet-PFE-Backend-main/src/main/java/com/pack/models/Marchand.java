package com.pack.models;

public class Marchand {
    private Long id ;
    private String username ; 
    private String telephone;
    private String nomGouvernerat;
    private String libelle;
    public Marchand() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getNomGouvernerat() {
        return nomGouvernerat;
    }
    public void setNomGouvernerat(String nomGouvernerat) {
        this.nomGouvernerat = nomGouvernerat;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public static Marchand fromEntity(User user)
    {
        if(user == null)
        {
            return null ;
        }

        Marchand marchand = new Marchand();
        marchand.setId(user.getId());
        marchand.setUsername(user.getUsername());
        marchand.setTelephone(user.getTelephone());
        marchand.setLibelle(user.getLibelle());
        marchand.setNomGouvernerat(user.getGouvernorat().getNom());

        return marchand;



    }
    
}
