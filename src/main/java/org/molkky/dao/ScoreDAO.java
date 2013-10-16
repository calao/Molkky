package org.molkky.dao;

import org.molkky.entities.ScoreEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 10/16/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public interface ScoreDAO extends AbstractDAO<ScoreEntity, Integer> {

    ScoreEntity getScoreByMancheAndMemberAndPartie(int manche, int idMembre, int partie);
}
