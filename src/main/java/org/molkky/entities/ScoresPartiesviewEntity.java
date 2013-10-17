package org.molkky.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 10/17/13
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "ScoresPartiesview", schema = "", catalog = "MolkkyDB")
@Entity
public class ScoresPartiesviewEntity implements Serializable {


    private static final long serialVersionUID = 4923697651063600530L;
    private ScoresClassementViewEntityId id;

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "score", column = @Column(name = "score")),
            @AttributeOverride(name = "idMember", column = @Column(name = "idMember")),
            @AttributeOverride(name = "idPartie", column = @Column(name = "idPartie")),
            @AttributeOverride(name = "nom", column = @Column(name = "nom")),
            @AttributeOverride(name = "prenom", column = @Column(name = "prenom")),
            @AttributeOverride(name = "idTournoi", column = @Column(name = "idTournoi"))
    })
    public ScoresClassementViewEntityId getId() {
        return id;
    }

    public void setId(ScoresClassementViewEntityId id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScoresPartiesviewEntity that = (ScoresPartiesviewEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


}
