package org.molkky.dao.impl;


import org.hibernate.SessionFactory;
import org.molkky.dao.TournoiDAO;
import org.molkky.entities.TournoiEntity;
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
public class TournoiDAOHibernateImpl extends AbstractDAOHibernateImpl<TournoiEntity, Integer> implements TournoiDAO {

    @Autowired
    public TournoiDAOHibernateImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }


}
