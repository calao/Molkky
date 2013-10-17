package org.molkky.dao.impl;


import com.google.common.base.Preconditions;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.molkky.dao.EquipeDAO;
import org.molkky.dao.ScoreDAO;
import org.molkky.entities.EquipeEntity;
import org.molkky.entities.ScoreEntity;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 2/26/13
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class EquipeDAOHibernateImpl extends AbstractDAOHibernateImpl<EquipeEntity, Integer> implements EquipeDAO {

    private ScoreDAO scoreDAO;

    @Autowired
    public EquipeDAOHibernateImpl(SessionFactory sessionFactory, ScoreDAO scoreDAO) {
        setSessionFactory(sessionFactory);
        this.scoreDAO = scoreDAO;
    }

    public Integer getMaxNumberByPartie(Integer idPartie) {
        
        Criteria criteria = getSession().createCriteria(EquipeEntity.class);
        
        criteria.setProjection(Projections.max("numeroEquipe"));
        criteria.add(Restrictions.eq("idPartie",idPartie));
        Integer maxVal = (Integer) criteria.uniqueResult();
        return  maxVal!=null ? maxVal : 0;
    }

    public boolean checkNumber(Integer idPartie, Integer num) {
        Criteria criteria = getSession().createCriteria(EquipeEntity.class);
        criteria.add(Restrictions.eq("numeroEquipe", num));
        criteria.add(Restrictions.eq("idPartie",idPartie));

        return criteria.list().size()>0 ;
    }

    public List<EquipeEntity> findAllByPartie(Integer idPartie) {
        Criteria criteria = getSession().createCriteria(EquipeEntity.class);
        criteria.add(Restrictions.eq("idPartie",idPartie));

        return criteria.list() ;
    }

    @Override
    public void delete(EquipeEntity equipeEntity) {
        Preconditions.checkArgument(equipeEntity != null, "persistentObject is required");
        List<ScoreEntity> scores = scoreDAO.getAllScoresForEquipe(equipeEntity);
        scoreDAO.deleteAll(scores);
        super.getHibernateTemplate().delete(equipeEntity);
    } 
}
