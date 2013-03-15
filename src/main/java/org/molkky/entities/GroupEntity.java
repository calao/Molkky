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
@javax.persistence.Table(name = "Groups", schema = "", catalog = "MolkkyDB")
@Entity
public class GroupEntity implements java.io.Serializable  {
    private int idGroup;

    @javax.persistence.Column(name = "idGroup")
    @Id
    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    private String numeroGroup;

    @javax.persistence.Column(name = "numero_Group")
    @Basic
    public String getNumeroGroup() {
        return numeroGroup;
    }

    public void setNumeroGroup(String numeroGroup) {
        this.numeroGroup = numeroGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupEntity that = (GroupEntity) o;

        if (idGroup != that.idGroup) return false;
        if (numeroGroup != null ? !numeroGroup.equals(that.numeroGroup) : that.numeroGroup != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idGroup;
        result = 31 * result + (numeroGroup != null ? numeroGroup.hashCode() : 0);
        return result;
    }
}
