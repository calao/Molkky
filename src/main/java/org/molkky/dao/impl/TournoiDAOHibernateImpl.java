package org.molkky.dao.impl;


import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.molkky.dao.TournoiDAO;
import org.molkky.entities.TournoiEntity;
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
public class TournoiDAOHibernateImpl extends AbstractDAOHibernateImpl<TournoiEntity, Integer> implements TournoiDAO {

    @Autowired
    public TournoiDAOHibernateImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public TournoiEntity findLast() {
        Criteria criteria = getSession().createCriteria(getDomainClass());
        Date date = (Date) criteria.setProjection(Projections.max("startDate")).uniqueResult();
        if(date!=null)
        {
        criteria = getSession().createCriteria(getDomainClass());
        criteria.add(Restrictions.eq("startDate", date));
        List<TournoiEntity> tournois = (List<TournoiEntity>) criteria.list();
        return tournois == null ? null : tournois.get(0);
        }
        return null;
    }
}
