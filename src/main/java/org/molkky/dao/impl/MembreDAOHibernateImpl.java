package org.molkky.dao.impl;


import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.molkky.dao.MembreDAO;
import org.molkky.entities.EquipeEntity;
import org.molkky.entities.MembreEntity;
import org.molkky.services.MembreComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 2/26/13
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class MembreDAOHibernateImpl extends AbstractDAOHibernateImpl<MembreEntity, Integer> implements MembreDAO {

    @Autowired
    public MembreDAOHibernateImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public List<MembreEntity> getAllWithoutEquipeByPartie(int idPartie) {

        Criteria criteria = getSession().createCriteria(EquipeEntity.class);
        criteria.setProjection(Projections.property("idMembre1"));
        List<Integer> listMemberId = criteria.add(Restrictions.eq("idPartie", idPartie)).list();

        criteria = getSession().createCriteria(EquipeEntity.class);
        criteria.setProjection(Projections.property("idMembre2"));
        listMemberId.addAll(criteria.add(Restrictions.eq("idPartie", idPartie)).list());

        criteria = getSession().createCriteria(MembreEntity.class);
        if(listMemberId!=null && listMemberId.size()>0)
        criteria.add(Restrictions.not(Restrictions.in("idMembre", listMemberId)));

        List<MembreEntity> listMembre  = criteria.list();

        Collections.sort(listMembre, new MembreComparator());
                
        return listMembre;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<MembreEntity> getAllWithoutEquipeByPartieAndNotAlreadySelected(int idPartie, int selectedMember) {
        Criteria criteria = getSession().createCriteria(EquipeEntity.class);
        criteria.setProjection(Projections.property("idMembre1"));
        List<Integer> listMemberId = criteria.add(Restrictions.eq("idPartie", idPartie)).list();

        criteria = getSession().createCriteria(EquipeEntity.class);
        criteria.setProjection(Projections.property("idMembre2"));
        listMemberId.addAll(criteria.add(Restrictions.eq("idPartie", idPartie)).list());

        criteria = getSession().createCriteria(MembreEntity.class);
        if(listMemberId!=null && listMemberId.size()>0)
            criteria.add(Restrictions.not(Restrictions.in("idMembre", listMemberId)));

        criteria.add(Restrictions.not(Restrictions.eq("idMembre", selectedMember)));

        return criteria.list();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<MembreEntity> getAllByPartie(int idPartie) {
        Criteria criteria = getSession().createCriteria(EquipeEntity.class);
        criteria.setProjection(Projections.property("idMembre1"));
        List<Integer> listMemberId = criteria.add(Restrictions.eq("idPartie", idPartie)).list();

        criteria = getSession().createCriteria(EquipeEntity.class);
        criteria.setProjection(Projections.property("idMembre2"));
        listMemberId.addAll(criteria.add(Restrictions.eq("idPartie", idPartie)).list());

        List<MembreEntity> list = new ArrayList<MembreEntity>();

        if(listMemberId!=null && listMemberId.size()>0)
        {   criteria = getSession().createCriteria(MembreEntity.class);
            criteria.add(Restrictions.in("idMembre", listMemberId));
        list = criteria.list();
        }


        return list ;  //To change body of implemented methods use File | Settings | File Templates.

    }

    public List<MembreEntity> getAllByPartieAndEquipe(int idPartie, int numeroEquipe) {
        Criteria criteria = getSession().createCriteria(EquipeEntity.class);
        criteria.setProjection(Projections.property("idMembre1"));
        List<Integer> listMemberId = criteria.add(Restrictions.eq("idPartie", idPartie)).add(Restrictions.eq("numeroEquipe", numeroEquipe)).list();

        criteria = getSession().createCriteria(EquipeEntity.class);
        criteria.setProjection(Projections.property("idMembre2"));
        listMemberId.addAll(criteria.add(Restrictions.eq("idPartie", idPartie)).add(Restrictions.eq("numeroEquipe", numeroEquipe)).list());

        List<MembreEntity> list = new ArrayList<MembreEntity>();

        if(listMemberId!=null && listMemberId.size()>0)
        {   criteria = getSession().createCriteria(MembreEntity.class);
            criteria.add(Restrictions.in("idMembre", listMemberId));
            list = criteria.list();
        }


        return list ;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
