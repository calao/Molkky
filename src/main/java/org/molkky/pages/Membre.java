package org.molkky.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.ActionLink;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.BeanModelSource;
import org.molkky.dao.MembreDAO;
import org.molkky.entities.MembreEntity;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 3/15/13
 * Time: 4:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Membre {

    @Property
    private BeanModel<MembreEntity> membreModel;

    @Inject
    private BeanModelSource beanModelSource;

    @Property
    private MembreEntity currentMembre;

    @Property
    private List<MembreEntity> membresList;



    @Property
    private Form addMembreForm;

    @Component
    private Zone membreFormZone;

    @Component(id = "delete")
    private ActionLink delete;

    @Inject
    private Messages messages;

    @Property
    private String prenom, nom, email, pseudonyme;

    @Property
    private Date anniversaire;


    @Inject
    private MembreDAO membreDAO;

    void setupRender(){
        membreModel = beanModelSource.createDisplayModel(MembreEntity.class, messages);
        membreModel.add("delete", null);
        membreModel.include("pseudonyme", "nom","prenom", "delete");
        membresList = membreDAO.findAll();
    }

    void onActionFromDelete(int id)
    {
        membreDAO.delete(membreDAO.findById(id));
    }
    public JSONObject getParams() {
        JSONObject options = new JSONObject();
        options.put("title", "Créer un nouveau membre");
        options.put("width", 520);
        return options;
    }

    @OnEvent(value = EventConstants.SUCCESS, component = "addMembreForm")
    void addMembre(){
        membreDAO.save(new MembreEntity(prenom, nom, email, anniversaire, pseudonyme)) ;
    }



}
