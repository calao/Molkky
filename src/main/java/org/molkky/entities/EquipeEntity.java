package org.molkky.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 2/25/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Equipes", schema = "", catalog = "MolkkyDB")
@Entity
public class EquipeEntity implements java.io.Serializable {



    private int idEquipe;

    @javax.persistence.Column(name = "idEquipe")
    @Id
    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    private int numeroEquipe;

    @javax.persistence.Column(name = "numero_equipe")
    @Basic
    public int getNumeroEquipe() {
        return numeroEquipe;
    }

    public void setNumeroEquipe(int numeroEquipe) {
        this.numeroEquipe = numeroEquipe;

    }

    public int idMembre1;

    @Column(name="idMembre1")
    public int getIdMembre1() {
        return idMembre1;
    }

    public void setIdMembre1(int idMembre1) {
        this.idMembre1 = idMembre1;
    }

    public int idMembre2;

    @Column(name="idMembre2")
    public int getIdMembre2() {
        return idMembre2;
    }

    public void setIdMembre2(int idMembre2) {
        this.idMembre2 = idMembre2;
    }

    public int idPartie;

    @Column(name="idPartie")
    public int getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(int idPartie) {
        this.idPartie = idPartie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquipeEntity that = (EquipeEntity) o;

        if (idEquipe != that.idEquipe) return false;
        if (numeroEquipe != that.numeroEquipe) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEquipe;
        result = 31 * result + numeroEquipe;
        return result;
    }
}
