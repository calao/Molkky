package org.molkky.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.ActionLink;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.BeanModelSource;
import org.molkky.dao.PartieDAO;
import org.molkky.entities.PartieEntity;
import org.molkky.entities.TournoiEntity;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 3/22/13
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Partie {


    @Property
    private BeanModel<PartieEntity> partieModel;

    @Property
    private PartieEntity currentPartie;

    @Property
    private List<PartieEntity> partiesList;

    @Inject
    private BeanModelSource beanModelSource;

    @Property
    private Form addPartieForm;

    @Component
    private Zone partieFormZone;

    @SessionState(create = false)
    private TournoiEntity selectedTournoi;

    private boolean selectedTournoiExists;

    @Component(id = "delete")
    private ActionLink delete;

    @Inject
    private Messages messages;

    @Property
    private Date date = new Date();

    @Property
    private String lieu;


    @Inject
    private PartieDAO partieDAO;

    void setupRender(){
        partieModel = beanModelSource.createDisplayModel(PartieEntity.class, messages);
        partieModel.add("datePartie", null);
        partieModel.add("delete", null);
        partieModel.include("lieu","datePartie","delete");
        if(selectedTournoiExists)
        {partiesList = partieDAO.findAllByTournoi(selectedTournoi.getIdTournoi());
        }

    }

    @Inject
    private AlertManager alertManager;
    void onActionFromDelete(int id)
    {
        try{
        partieDAO.delete(partieDAO.findById(id));
        }catch(Exception e)
        {
         alertManager.alert(Duration.SINGLE, Severity.ERROR, "Vous ne pouvez pas supprimer une partie pour laquelle vous avez déjà encodé des points ou des equipes");
        }
    }
    public JSONObject getParams() {
        JSONObject options = new JSONObject();
        options.put("title", "Créer une nouvelle Partie");
        options.put("width", 520);
        return options;
    }

    @OnEvent(value = EventConstants.SUCCESS, component = "addPartieForm")
    void addTournoi(){

        if(date!=null && lieu!=null)
        {
            partieDAO.save(new PartieEntity(date, lieu, selectedTournoi.getIdTournoi()));
        }
    }

    public String getDateString(){
        return removeTime(currentPartie.getDate().toString());
    }

    public String removeTime(String date)
    {
        return date.replace(" 00:00:00.0","");
    }

}
