package org.molkky.dao;

import org.molkky.entities.PartieEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 2/26/13
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */

public interface PartieDAO extends AbstractDAO<PartieEntity, Integer> {
    List<PartieEntity> findAllByTournoi(int idTournoi);

    PartieEntity findLastByTournoi(int idTournoi);
}
