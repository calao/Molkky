package org.molkky.dao;

import java.util.List;

/**
 * Abstract dao.
 *
 * @param <E>       the entity.
 * @param <KeyType> the identifier of the entity.
 */
public interface AbstractDAO<E, KeyType> {

    /**
     * Insert the entity into the database.
     *
     * @param newInstance
     * @return the key.
     */
    KeyType save(E newInstance);

    /**
     * updates the entity.
     *
     * @param transientObject
     */
    void update(E transientObject);

    /**
     * Insert or updates the entity into the db but does not return the inserted key.
     *
     * @param transientObject
     */
    void saveOrUpdate(E transientObject);

    /**
     * Deletes an entity in the db.
     *
     * @param persistentObject
     */
    void delete(E persistentObject);


    /**
     * Finds an entity by its identifier.
     *
     * @param id
     * @return
     */
    E findById(KeyType id);

    /**
     * looks up all entries in the db of this entity.
     *
     * @return
     */
    List<E> findAll();
}
