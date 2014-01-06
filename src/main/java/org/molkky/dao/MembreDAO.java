package org.molkky.dao;

import org.molkky.entities.MembreEntity;
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
public interface MembreDAO extends AbstractDAO<MembreEntity, Integer> {

    List<MembreEntity> getAllWithoutEquipeByPartie(int idPartie);

    List<MembreEntity> getAllWithoutEquipeByPartieAndNotAlreadySelected(int idPartie, int selectedMember);

    List<MembreEntity> getAllByPartie(int idPartie);

    List<MembreEntity> getAllByPartieAndEquipe(int idPartie, int numeroEquipe);

}
