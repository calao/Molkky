package org.molkky.dao.impl;


import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.molkky.dao.PartieDAO;
import org.molkky.entities.PartieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 2/26/13
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class PartieDAOHibernateImpl extends AbstractDAOHibernateImpl<PartieEntity, Integer> implements PartieDAO {

    @Autowired
    public PartieDAOHibernateImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);

    }
    
    public List<PartieEntity> findAllByTournoi(int idTournoi){
        Criteria criteria = getSession().createCriteria(getDomainClass());
        criteria.add(Restrictions.eq("idTournoi", idTournoi));

        return criteria.list();
    }

    public PartieEntity findLastByTournoi(int idTournoi) {
        Criteria criteria = getSession().createCriteria(getDomainClass());
        Date date = (Date) criteria.setProjection(Projections.max("date")).uniqueResult();
        criteria = getSession().createCriteria(getDomainClass());
        criteria.add(Restrictions.eq("date", date));
        criteria.add(Restrictions.eq("idTournoi", idTournoi));
        List<PartieEntity> partie = (List<PartieEntity>) criteria.list();
        return partie.size()>0 ? partie.get(0) : null;
    }
}
