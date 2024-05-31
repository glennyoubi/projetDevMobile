package com.example.myapplication;

public class employeur {
    private String email, password, nomEnt, phone, ville, id;

   public employeur(){}

    public employeur(String email, String password, String nomEnt, String phone, String ville, String id) {
        this.email = email;
        this.password = password;
        this.nomEnt = nomEnt;
        this.phone = phone;
        this.ville = ville;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nomEnt;
    }

    public void setNom(String nom) {
        this.nomEnt = nom;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
