package org.molkky.dao.impl;

import com.google.common.base.Preconditions;
import org.hibernate.Criteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


public abstract class AbstractDAOHibernateImpl<T extends Serializable, KeyType extends Serializable> extends HibernateDaoSupport {

    protected Class<T> domainClass = getDomainClass();

    protected Class<T> getDomainClass() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof Class) {
            throw new RuntimeException("Missing type parameter!");
        }
        //noinspection unchecked
        return (Class<T>) ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }


    protected Criteria getCriteria(String alias) {
        return getSession().createCriteria(getDomainClass(), alias);
    }

    public KeyType save(T newInstance) {
        Preconditions.checkArgument(newInstance != null, "newInstance is required");
        //noinspection unchecked
        return (KeyType) getHibernateTemplate().save(newInstance);
    }

    public void update(T transientObject) {
        Preconditions.checkArgument(transientObject != null, "transientObject is required");
        getHibernateTemplate().update(transientObject);
    }

    public void saveOrUpdate(T transientObject) {
        Preconditions.checkArgument(transientObject != null, "transientObject is required");
        getHibernateTemplate().saveOrUpdate(transientObject);
    }

    public void delete(T persistentObject) {
        Preconditions.checkArgument(persistentObject != null, "persistentObject is required");
        getHibernateTemplate().delete(persistentObject);
    }

    public T findById(KeyType id) {
        Preconditions.checkArgument(id != null, "Id is required");
        return getHibernateTemplate().get(domainClass, id);
    }

    public List<T> findAll() {
        return getHibernateTemplate().loadAll(getDomainClass());
    }

}
