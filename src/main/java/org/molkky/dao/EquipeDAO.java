package org.molkky.dao;

import org.apache.commons.collections.iterators.ListIteratorWrapper;
import org.molkky.entities.EquipeEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 2/26/13
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public interface EquipeDAO extends AbstractDAO<EquipeEntity, Integer> {

    Integer getMaxNumberByPartie(Integer idPartie);
    boolean checkNumber(Integer idPartie, Integer num);
    List<EquipeEntity> findAllByPartie(Integer idPartie);
    List<Integer> getAllEquipeNumberByPartie(Integer idPartie);

}
