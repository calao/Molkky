package org.molkky.components;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;
import org.molkky.dao.PartieDAO;
import org.molkky.dao.TournoiDAO;
import org.molkky.entities.PartieEntity;
import org.molkky.entities.TournoiEntity;

import java.util.ArrayList;
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
    private SelectModel tournoiSelectModel, partieSelectModel;

    @Component
    private Form selectTournoiForm, selectPartieForm;

    @Inject
    SelectModelFactory selectModelFactory;

    @SessionState
    @Property
    private TournoiEntity selectedTournoi;

    @SessionState
    @Property
    private PartieEntity selectedPartie;

    private boolean selectedPartieExists;
    private boolean selectedTournoiExists;

    @Component
    private Select tournoiSelection;
    @Component
    private Select partieSelection;

    @Inject
    TournoiDAO tournoiDAO;

    @Inject
    PartieDAO partieDAO;

    private List<TournoiEntity> tournois = new ArrayList<TournoiEntity>();
    private List<PartieEntity> parties = new ArrayList<PartieEntity>();

    void setupRender() {

        tournois = tournoiDAO.findAll();
        if (!selectedTournoiExists)
            selectedTournoi = tournoiDAO.findLast();

        if (tournois.size() != 0) {
            tournoiSelectModel = selectModelFactory.create(tournois, "label");

            if (selectedTournoiExists)
            {if (selectedTournoi != null)
                { parties = partieDAO.findAllByTournoi(selectedTournoi.getIdTournoi());
                  if(!selectedPartieExists || selectedPartie==null || !parties.contains(selectedPartie))
                  selectedPartie = partieDAO.findLastByTournoi(selectedTournoi.getIdTournoi());
                }
            }

            if (parties.size() != 0) {
                partieSelectModel = selectModelFactory.create(parties, "label");
            }
        }

    }

    public boolean isPartie() {
        return parties != null && parties.size()>0;
    }

    public boolean isTournoi() {
        return tournois != null && tournois.size()>0;
    }

}
