package org.molkky.entities;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.nio.MappedByteBuffer;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 2/25/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Members", schema = "", catalog = "MolkkyDB")
@Entity
public class MembreEntity implements java.io.Serializable  {

    public MembreEntity() {
    }

    public MembreEntity(String prenom, String nom, String email, Date anniversaire) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.anniversaire = anniversaire;
    }

    private int idMembre;

    @javax.persistence.Column(name = "idMember")
    @Id
    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    private String prenom;

    @javax.persistence.Column(name = "prenom")
    @Basic
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    private String nom;

    @javax.persistence.Column(name = "nom")
    @Basic
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private String email;

    @javax.persistence.Column(name = "email")
    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private Date anniversaire;

    @javax.persistence.Column(name = "anniversaire")
    @Basic
    public Date getAnniversaire() {
        return anniversaire;
    }

    public void setAnniversaire(Date anniversaire) {
        this.anniversaire = anniversaire;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MembreEntity that = (MembreEntity) o;

        if (idMembre != that.idMembre) return false;
        if (anniversaire != null ? !anniversaire.equals(that.anniversaire) : that.anniversaire != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (prenom != null ? !prenom.equals(that.prenom) : that.prenom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMembre;
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (anniversaire != null ? anniversaire.hashCode() : 0);
        return result;
    }

    @Transient
    public String getLabel(){
        return prenom + " " + nom;
    }

    @Override
    public String toString() {
        return String.valueOf(idMembre);
    }

    public List<EquipeEntity> equipes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "membre1")
    public List<EquipeEntity> getEquipes (){
        return equipes;
    }

    public void setEquipes(List<EquipeEntity> equipes) {
        this.equipes = equipes;
    }

    public List<EquipeEntity> equipes2;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "membre2")
    public List<EquipeEntity> getEquipes2 (){
        return equipes2;
    }

    public void setEquipes2(List<EquipeEntity> equipes2) {
        this.equipes2 = equipes2;
    }
}

