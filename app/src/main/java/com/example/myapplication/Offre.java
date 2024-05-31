package com.example.myapplication;

public class Offre
{


    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getdDebut() {
        return dDebut;
    }

    public void setdDebut(String dDebut) {
        this.dDebut = dDebut;
    }

    public String getdFin() {
        return dFin;
    }

    public void setdFin(String dFin) {
        this.dFin = dFin;
    }

    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }

    public String getNomEnt() {
        return nomEnt;
    }

    public void setNomEnt(String nomEnt) {
        this.nomEnt = nomEnt;
    }

    public String getid_employeur() {
        return id_employeur;
    }

    public void setid_employeur(String id) {
        this.id_employeur = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Offre(String poste, String localisation, String dDebut, String dFin, String rem, String nomEnt, String id_employeur,String id) {
        this.poste = poste;
        this.localisation = localisation;
        this.dDebut = dDebut;
        this.dFin = dFin;
        this.rem = rem;
        this.nomEnt = nomEnt;
        this.id_employeur = id_employeur;
        this.id = id;
    }

    public Offre(){}
    private String poste, localisation, dDebut, dFin, rem, nomEnt, id_employeur, id;



}
