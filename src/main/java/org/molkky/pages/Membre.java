package org.molkky.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
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
    private String prenom, nom, email;

    @Property
    private Date anniversaire;


    @Inject
    private MembreDAO membreDAO;

    @Inject
    private AlertManager alertManager;

    void setupRender(){
        membreModel = beanModelSource.createDisplayModel(MembreEntity.class, messages);
        membreModel.add("delete", null);
        membreModel.include("nom","prenom", "delete");
        membresList = membreDAO.findAll();
    }

    void onActionFromDelete(int id)
    {
        try {
         membreDAO.delete(membreDAO.findById(id));
        }catch(Exception e) {
         alertManager.alert(Duration.SINGLE, Severity.ERROR, "Vous ne pouvez pas supprimer un membre qui a déjà participé à un tournois");
        }

    }
    public JSONObject getParams() {
        JSONObject options = new JSONObject();
        options.put("title", "Créer un nouveau membre");
        options.put("width", 520);
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", true);
        return options;
    }

    @OnEvent(value = EventConstants.SUCCESS, component = "addMembreForm")
    void addMembre(){
        membreDAO.save(new MembreEntity(prenom, nom, email, anniversaire)) ;
    }



}
