package org.molkky.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.PropertyConduit;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.beaneditor.PropertyModel;
import org.apache.tapestry5.beaneditor.RelativePosition;
import org.apache.tapestry5.corelib.components.ActionLink;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.BeanModelSource;
import org.got5.tapestry5.jquery.components.Dialog;
import org.molkky.dao.TournoiDAO;
import org.molkky.entities.TournoiEntity;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 3/15/13
 * Time: 4:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tournoi {

    @Property
    private BeanModel<TournoiEntity> tournoiModel;

    @Property
    private TournoiEntity currentTournoi;

    @Property
    private List<TournoiEntity> tournoisList;

    @Inject
    private BeanModelSource beanModelSource;

    @Property
    private Form addTournoiForm;

    @Component
    private Zone tournoiFormZone;

    @Component(id = "delete")
    private ActionLink delete;

    @Inject
    private Messages messages;

    @Property
    private Date startDate, endDate;

    @Property
    private String nom;


    @Inject
    private TournoiDAO tournoiDAO;

    void setupRender(){
        tournoiModel = beanModelSource.createDisplayModel(TournoiEntity.class, messages);
        tournoiModel.add("debut", null);
        tournoiModel.add("fin", null);
        tournoiModel.add("delete", null);
        tournoiModel.include("nom", "debut","fin", "delete");
        tournoisList = tournoiDAO.findAll();
    }

    void onActionFromDelete(int id)
    {
       tournoiDAO.delete(tournoiDAO.findById(id));
    }
    public JSONObject getParams() {
        JSONObject options = new JSONObject();
        options.put("title", "Créer un nouveau tournoi");
        options.put("width", 520);
        return options;
    }

    @OnEvent(value = EventConstants.SUCCESS, component = "addTournoiForm")
    void addTournoi(){

        if(startDate!=null && endDate!=null)
        {
         int id = (nom == null ?  tournoiDAO.save(new TournoiEntity(startDate, endDate)) : tournoiDAO.save(new TournoiEntity(nom,startDate, endDate)));
        }
    }

    public String getStartDateString(){
       return removeTime(currentTournoi.getStartDate().toString());
    }

    public String getEndDateString(){
      return removeTime(currentTournoi.getEndDate().toString());
    }

    public String removeTime(String date)
    {
        return date.replace(" 00:00:00.0","");
    }



}
