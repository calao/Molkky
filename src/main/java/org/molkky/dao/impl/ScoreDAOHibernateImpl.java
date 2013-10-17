package org.molkky.dao.impl;


import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.molkky.dao.ScoreDAO;
import org.molkky.entities.ScoreEntity;
import org.molkky.entities.ScoresPartiesviewEntity;
import org.molkky.entities.ScoresTournoiViewEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 2/26/13
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ScoreDAOHibernateImpl extends AbstractDAOHibernateImpl<ScoreEntity, Integer> implements ScoreDAO {

    @Autowired
    public ScoreDAOHibernateImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public ScoreEntity getScoreByMancheAndMemberAndPartie(int manche, int idMembre, int idPartie) {
        Criteria criteria = getSession().createCriteria(ScoreEntity.class);
        criteria.add(Restrictions.eq("manche", manche));
        criteria.add(Restrictions.eq("idMembre", idMembre));
        criteria.add(Restrictions.eq("idPartie", idPartie));
        return (ScoreEntity) criteria.uniqueResult();          //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<ScoresPartiesviewEntity> getClassementByPartie(int partie) {
        Criteria criteria = getSession().createCriteria(ScoresPartiesviewEntity.class);
        criteria.addOrder(Order.desc("id.score"));
        criteria.add(Restrictions.eq("id.idPartie", partie));

        return criteria.list();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<ScoresTournoiViewEntity> getClassementByTournoi(int tournoi) {
        Criteria criteria = getSession().createCriteria(ScoresTournoiViewEntity.class);
        criteria.addOrder(Order.desc("id.score"));
        criteria.add(Restrictions.eq("id.idTournoi", tournoi));

        return criteria.list();  //To change body of implemented methods use File | Settings | File Templates.
    }
}
