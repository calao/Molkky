package org.molkky.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 2/25/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Parties")
@Entity
public class PartieEntity implements java.io.Serializable  {
    private int idPartie;
    private Set<ScoreEntity> scores;

    @javax.persistence.Column(name = "idPartie")
    @Id
    public int getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(int idPartie) {
        this.idPartie = idPartie;
    }

    private Date date;

    @javax.persistence.Column(name = "date")
    @Basic
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private String lieu;

    @javax.persistence.Column(name = "lieu")
    @Basic
    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    private int idTournoi;

    @javax.persistence.Column(name = "idTournoi")
    @Basic
    public int getIdTournoi() {
        return idTournoi;
    }

    public void setIdTournoi(int idTournoi) {
        this.idTournoi = idTournoi;
    }



    public PartieEntity() {
    }

    public PartieEntity(Date date, String lieu, int idTournoi) {
        this.date = date;
        this.lieu = lieu;
        this.idTournoi = idTournoi;
    }

    @OneToMany(mappedBy = "partie")
    public Set<ScoreEntity> getScores() {
        return scores;
    }

    public void setScores(Set<ScoreEntity> scores) {
        this.scores = scores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartieEntity that = (PartieEntity) o;

        if (idPartie != that.idPartie) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (lieu != null ? !lieu.equals(that.lieu) : that.lieu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPartie;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (lieu != null ? lieu.hashCode() : 0);
        return result;
    }

    @Transient
    public String getLabel(){
        return "le "+date.toString().replace(" 00:00:00.0","")+">"+lieu;
    }
}
