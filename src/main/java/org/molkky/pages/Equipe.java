package org.molkky.pages;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.SelectModelFactory;
import org.molkky.dao.EquipeDAO;
import org.molkky.dao.MembreDAO;
import org.molkky.entities.EquipeEntity;
import org.molkky.entities.MembreEntity;

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
    private int numeroEquipe;

    @Property
    private Select membre1, membre2;

    @Property
    private MembreEntity selectedMembre1, selectedMembre2;

    @Property
    private List<MembreEntity> membres;

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

    void setupRender() {

        membres = membreDAO.getAllWithoutEquipeByPartie(1);
        numeroEquipe = equipeDAO.getMaxNumberByPartie(1);
        membreSelectModel = selectModelFactory.create(membres, "label");
        equipeModel = beanModelSource.createDisplayModel(EquipeEntity.class, messages);
        equipeModel.add("membre1", null);
        equipeModel.add("membre2", null);
        equipeModel.include("numeroEquipe", "membre1", "membre2");
    }

    public ValueEncoder<MembreEntity> getMembreEncoder() {

        return new ValueEncoder<MembreEntity>() {

            public String toClient(MembreEntity value) {
                // return the given object's ID
                return String.valueOf(value.getIdMembre());
            }

            public MembreEntity toValue(String id) {
                // find the color object of the given ID in the database
                return membreDAO.findById(Integer.parseInt(id));
            }

            public ValueEncoder<MembreEntity> create(Class<MembreEntity> type) {
                return this;
            }
        };
    }

    public String getMembre1Label() {
        return membreDAO.findById(currentEquipe.getIdMembre1()).getLabel();
    }

    public String getMembre2Label() {
        return membreDAO.findById(currentEquipe.getIdMembre2()).getLabel();
    }
}