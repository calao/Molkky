package org.molkky.dao;

import org.molkky.entities.TournoiEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 2/26/13
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public interface TournoiDAO extends AbstractDAO<TournoiEntity, Integer> {

    TournoiEntity findLast();
}
