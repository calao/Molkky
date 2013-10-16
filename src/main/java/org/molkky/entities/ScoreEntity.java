package org.molkky.entities;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 2/25/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "Scores", schema = "", catalog = "MolkkyDB")
@Entity
public class ScoreEntity implements java.io.Serializable  {
    private int idScore;
    private int manche;
    private MembreEntity membre;
    private int idMembre;
    private PartieEntity partie;
    private int idPartie;
    private int score;

    public ScoreEntity() {

    }

    public ScoreEntity(int manche, int idMembre, int idPartie, int score) {
        this.manche = manche;
        this.idMembre = idMembre;
        this.idPartie = idPartie;
        this.score = score;
    }

    @javax.persistence.Column(name = "idScore")
    @Id
    public int getIdScore() {
        return idScore;
    }

    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }

    @javax.persistence.Column(name = "score")
    @Basic
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @ManyToOne
    @JoinColumn(name = "idMember", updatable = false, insertable = false)
    public MembreEntity getMembre() {
        return membre;
    }

    public void setMembre(MembreEntity membre) {
        this.membre = membre;
    }

    @Column(name = "manche")
    public int getManche() {
        return manche;
    }

    public void setManche(int manche) {
        this.manche = manche;
    }

    @Column(name = "idMember")
    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    @ManyToOne
    @JoinColumn(name = "idPartie", insertable = false, updatable = false)
    public PartieEntity getPartie() {
        return partie;
    }

    public void setPartie(PartieEntity partie) {
        this.partie = partie;
    }

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

        ScoreEntity that = (ScoreEntity) o;

        if (idMembre != that.idMembre) return false;
        if (idPartie != that.idPartie) return false;
        if (idScore != that.idScore) return false;
        if (manche != that.manche) return false;
        if (score != that.score) return false;
        if (membre != null ? !membre.equals(that.membre) : that.membre != null) return false;
        if (partie != null ? !partie.equals(that.partie) : that.partie != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idScore;
        result = 31 * result + manche;
        result = 31 * result + (membre != null ? membre.hashCode() : 0);
        result = 31 * result + idMembre;
        result = 31 * result + (partie != null ? partie.hashCode() : 0);
        result = 31 * result + idPartie;
        result = 31 * result + score;
        return result;
    }
}
