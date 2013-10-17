package org.molkky.dao;

import org.molkky.entities.ScoreEntity;
import org.molkky.entities.ScoresPartiesviewEntity;
import org.molkky.entities.ScoresTournoiViewEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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
    List<ScoresPartiesviewEntity> getClassementByPartie(int partie);
    List<ScoresTournoiViewEntity> getClassementByTournoi(int tournoi);
}
