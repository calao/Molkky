package org.molkky.dao;

import org.molkky.entities.EquipeEntity;
import org.molkky.entities.TournoiEntity;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 2/26/13
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */

public interface EquipeDAO extends AbstractDAO<EquipeEntity, Integer> {

    Integer getMaxNumberByPartie(Integer idPartie);
}
