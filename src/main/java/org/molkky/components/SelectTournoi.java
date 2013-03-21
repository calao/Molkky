package org.molkky.components;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;
import org.molkky.dao.TournoiDAO;
import org.molkky.entities.TournoiEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 3/18/13
 * Time: 7:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class SelectTournoi {

    @Property
    private SelectModel tournoiSelectModel;

    @Component
    private Form selectTournoiForm;

    @Inject
    SelectModelFactory selectModelFactory;

    @SessionState
    @Property
    private TournoiEntity selectedTournoi;

    private boolean selectedTournoiExists;

    @Component
    private Select tournoiSelection;

    @Inject
    TournoiDAO tournoiDAO;

    void setupRender() {
        // invoke my service to find all colors, e.g. in the database
        List<TournoiEntity> tournois = tournoiDAO.findAll();
        if(!selectedTournoiExists)
        selectedTournoi = tournoiDAO.findLast();
        // create a SelectModel from my list of colors
        tournoiSelectModel = selectModelFactory.create(tournois, "label");
    }

}
