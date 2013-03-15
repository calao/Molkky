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
@javax.persistence.Table(name = "Scores", schema = "", catalog = "MolkkyDB")
@Entity
public class ScoreEntity implements java.io.Serializable  {
    private int idScore;

    @javax.persistence.Column(name = "idScore")
    @Id
    public int getIdScore() {
        return idScore;
    }

    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }

    private String score;

    @javax.persistence.Column(name = "score")
    @Basic
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScoreEntity that = (ScoreEntity) o;

        if (idScore != that.idScore) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idScore;
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }
}
