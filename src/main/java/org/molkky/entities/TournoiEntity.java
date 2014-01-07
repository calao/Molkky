package org.molkky.entities;

import org.hibernate.engine.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 2/25/13
 * Time: 6:01 PM

 * To change this template use File | Settings | File Templates.
 */
@Table(name = "Tournois")
@Entity
public class TournoiEntity implements Serializable  {
    private int idTournoi;

    public TournoiEntity() {
    }

    public TournoiEntity(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TournoiEntity(String nom, Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.nom = nom;
    }

    @Column(name = "idTournoi")
    @Id
    public int getIdTournoi() {

        return idTournoi;
    }

    public void setIdTournoi(int idTournoi) {
        this.idTournoi = idTournoi;
    }

    private String nom;
    @Column(name="nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private Date startDate;

    @Column(name = "startDate")
    @Basic
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    private Date endDate;

    @Column(name = "endDate")
    @Basic
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TournoiEntity that = (TournoiEntity) o;

        if (idTournoi != that.idTournoi) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTournoi;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @Transient
    public String getLabel(){
        return nom + " du " + startDate.toString().replace("00:00:00.0","") +
                " au " + endDate.toString().replace("00:00:00.0","");
    }

}
