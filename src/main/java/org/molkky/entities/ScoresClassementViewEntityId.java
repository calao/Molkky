package org.molkky.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 10/17/13
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */

@Embeddable
public class ScoresClassementViewEntityId implements Serializable {


    private static final long serialVersionUID = 191064121644260072L;

    public ScoresClassementViewEntityId() {
    }

    public ScoresClassementViewEntityId(Long score, Integer idMember, Integer idPartie, Integer idTournoi, String prenom, String nom) {
        this.score = score;
        this.idMember = idMember;
        this.idPartie = idPartie;
        this.idTournoi = idTournoi;
        this.prenom = prenom;
        this.nom = nom;
    }

    private Long score;

   public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    private Integer idMember;

    public Integer getIdMember() {
        return idMember;
    }

    public void setIdMember(Integer idMember) {
        this.idMember = idMember;
    }

    private Integer idPartie;

    public Integer getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(Integer idPartie) {
        this.idPartie = idPartie;
    }

    private Integer idTournoi;

    public Integer getIdTournoi() {
        return idTournoi;
    }

    public void setIdTournoi(Integer idTournoi) {
        this.idTournoi = idTournoi;
    }

    private String prenom;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScoresClassementViewEntityId that = (ScoresClassementViewEntityId) o;

        if (idMember != null ? !idMember.equals(that.idMember) : that.idMember != null) return false;
        if (idPartie != null ? !idPartie.equals(that.idPartie) : that.idPartie != null) return false;
        if (idTournoi != null ? !idTournoi.equals(that.idTournoi) : that.idTournoi != null) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (prenom != null ? !prenom.equals(that.prenom) : that.prenom != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = score != null ? score.hashCode() : 0;
        result = 31 * result + (idMember != null ? idMember.hashCode() : 0);
        result = 31 * result + (idPartie != null ? idPartie.hashCode() : 0);
        result = 31 * result + (idTournoi != null ? idTournoi.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        return result;
    }
}
