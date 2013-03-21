package org.molkky.dao.impl;


import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.molkky.dao.EquipeDAO;
import org.molkky.entities.EquipeEntity;
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
public class EquipeDAOHibernateImpl extends AbstractDAOHibernateImpl<EquipeEntity, Integer> implements EquipeDAO {

    @Autowired
    public EquipeDAOHibernateImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public Integer getMaxNumberByPartie(Integer idPartie) {
        
        Criteria criteria = getSession().createCriteria(EquipeEntity.class);
        
        criteria.setProjection(Projections.max("numeroEquipe"));
        criteria.add(Restrictions.eq("idPartie",idPartie));
        Integer maxVal = (Integer) criteria.uniqueResult();
        return  maxVal!=null ? maxVal : 0;
    }
}
