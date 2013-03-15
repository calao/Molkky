package org.molkky.entities;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 2/25/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupLienEquipesEntityPK implements java.io.Serializable {
    private int idGroup;

    @Id
    @Column(name = "idGroup")
    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    private int idEquipe;

    @Id
    @Column(name = "idEquipe")
    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupLienEquipesEntityPK that = (GroupLienEquipesEntityPK) o;

        if (idEquipe != that.idEquipe) return false;
        if (idGroup != that.idGroup) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idGroup;
        result = 31 * result + idEquipe;
        return result;
    }
}
