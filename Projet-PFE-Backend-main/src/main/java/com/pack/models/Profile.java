package com.pack.models;

public class Profile {
    /*info marchand  */
    private Long id;

	private String username;


	private String telephone;


	private String libelle;

	private Gouvernorat gouvernorat;

    public Profile() {
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


    public static Profile fromEntity (User user)
    {
        Profile profile = new Profile();
        profile.setId(user.getId());
        profile.setUsername(user.getUsername());
        profile.setTelephone(user.getTelephone());
        profile.setLibelle(user.getLibelle());
        profile.setGouvernorat(user.getGouvernorat());
        return profile;
    }
    
}
