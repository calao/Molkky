package org.molkky.dao.impl;


import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.molkky.dao.ScoreDAO;
import org.molkky.entities.ScoreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
