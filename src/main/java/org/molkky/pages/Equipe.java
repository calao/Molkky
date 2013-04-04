package org.molkky.pages;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.SelectModelFactory;
import org.molkky.dao.EquipeDAO;
import org.molkky.dao.MembreDAO;
import org.molkky.entities.EquipeEntity;
import org.molkky.entities.MembreEntity;
import org.molkky.entities.PartieEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 3/19/13
 * Time: 7:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Equipe {

    @Property
    private Integer numeroEquipe = 1;

    @Property
    private Select membre1, membre2;

    @Property
    private Integer selectedMembre1, selectedMembre2;

    @Property
    private List<MembreEntity> membres = new ArrayList<MembreEntity>();

    @Property
    private BeanModel<EquipeEntity> equipeModel;

    @Property
    private SelectModel membreSelectModel;

    @Inject
    private SelectModelFactory selectModelFactory;

    @Inject
    private BeanModelSource beanModelSource;

    @Property
    private EquipeEntity currentEquipe;

    @Inject
    private Messages messages;

    @Inject
    private MembreDAO membreDAO;

    @Inject
    private EquipeDAO equipeDAO;

    @Property
    private List<EquipeEntity> equipesList;

    @Component
    private Form addEquipeForm;

    @SessionState(create = false)
    @Property
    private PartieEntity selectedPartie;

    private boolean selectedPartieExists;

    void setupRender() {

        if (selectedPartieExists && selectedPartie != null) {
            numeroEquipe = equipeDAO.getMaxNumberByPartie(selectedPartie.getIdPartie()) + 1;
            membres = membreDAO.getAllWithoutEquipeByPartie(selectedPartie.getIdPartie());
            equipesList = equipeDAO.findAllByPartie(selectedPartie.getIdPartie());
        }

        membreSelectModel = selectModelFactory.create(membres, "label");

        equipeModel = beanModelSource.createDisplayModel(EquipeEntity.class, messages);
        equipeModel.add("membre1", null);
        equipeModel.add("membre2", null);
        equipeModel.add("delete", null);
        equipeModel.include("numeroEquipe", "membre1", "membre2", "delete");
    }

    void onValidateFromAddEquipeForm() {
        if (!selectedPartieExists || selectedPartie == null) {
            addEquipeForm.recordError(messages.get("noPartie"));
        } else {
            if (numeroEquipe == null) {
                addEquipeForm.recordError(messages.get("numberNull"));
            } else if (equipeDAO.checkNumber(selectedPartie.getIdPartie(), numeroEquipe)) {
                addEquipeForm.recordError(messages.get("numberExist"));
            }

        }

        if (selectedMembre1 == null || selectedMembre2 == null) {
            addEquipeForm.recordError(messages.get("noMembre"));
        } else
            if(selectedMembre1 == selectedMembre2)
            {
                addEquipeForm.recordError(messages.get("membreEquals"));
            }


    }

    void onSuccessFromAddEquipeForm() {

        equipeDAO.save(new EquipeEntity(numeroEquipe, selectedMembre1, selectedMembre2, selectedPartie.getIdPartie()));
    }

    void onActionFromDelete(int id) {
        EquipeEntity equipe = new EquipeEntity();
        equipe.setIdEquipe(id);
        equipeDAO.delete(equipe);
    }

}