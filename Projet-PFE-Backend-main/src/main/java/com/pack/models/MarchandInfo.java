package com.pack.models;

public class MarchandInfo {
    // return info marchand et request update
    private long id;

    private String username;
    private String libelle;
    private String telephone;
    private Gouvernorat gouvernorat;

    public MarchandInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Gouvernorat getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(Gouvernorat gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public static MarchandInfo fromEntity(User user)
    {
        if(user == null)
        {
            return null ;
        }
        MarchandInfo marchandInfo = new MarchandInfo();
        marchandInfo.setId(user.getId());
        marchandInfo.setUsername(user.getUsername());
        marchandInfo.setLibelle(user.getLibelle());
        marchandInfo.setTelephone(user.getTelephone());
        marchandInfo.setGouvernorat(user.getGouvernorat());

        return  marchandInfo ;

    }
}
