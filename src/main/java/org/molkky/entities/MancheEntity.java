package org.molkky.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 2/25/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Manches", schema = "", catalog = "MolkkyDB")
@Entity
public class MancheEntity implements java.io.Serializable  {
    private int idManche;

    @javax.persistence.Column(name = "idManche")
    @Id
    public int getIdManche() {
        return idManche;
    }

    public void setIdManche(int idManche) {
        this.idManche = idManche;
    }

    private int numeroManche;

    @javax.persistence.Column(name = "numero_manche")
    @Basic
    public int getNumeroManche() {
        return numeroManche;
    }

    public void setNumeroManche(int numeroManche) {
        this.numeroManche = numeroManche;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MancheEntity that = (MancheEntity) o;

        if (idManche != that.idManche) return false;
        if (numeroManche != that.numeroManche) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idManche;
        result = 31 * result + numeroManche;
        return result;
    }
}
