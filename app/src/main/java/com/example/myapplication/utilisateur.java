package com.example.myapplication;

public class utilisateur {
    private String email, password, nom, prenom, phone, nat, ville, id;

    public utilisateur(){}

    public utilisateur(String email, String password, String nom, String prenom, String phone, String nat, String ville, String id) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.nat = nat;
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
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
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
