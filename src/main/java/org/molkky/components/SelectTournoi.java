package org.molkky.components;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.internal.services.SyntheticStackTraceElementAnalyzer;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.services.ajax.JavaScriptCallback;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
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

    @SessionState(create = false)
    @Property
    private TournoiEntity selectedTournoi;

    @SessionState(create = false)
    @Property
    private PartieEntity selectedPartie;

    @Component
    private Select tournoiSelection;
    @Component
    private Select partieSelection;

    @Inject
    private TournoiDAO tournoiDAO;

    @Inject
    private PartieDAO partieDAO;

    @InjectComponent
    private Zone selectTournoiZone;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @Persist
    private List<TournoiEntity> tournois;
    @Persist
    private List<PartieEntity> parties;

    void setupRender() {

        tournois = tournoiDAO.findAll();

        if (tournois.size() != 0) {

            tournoiSelectModel = selectModelFactory.create(tournois, "label");
            if (selectedTournoi == null || !tournois.contains(selectedTournoi)) {
                selectedTournoi = tournoiDAO.findLast();
            }
            parties = partieDAO.findAllByTournoi(selectedTournoi.getIdTournoi());
            if (parties.size() != 0) {
                if (selectedPartie == null || !parties.contains(selectedPartie)) {
                    selectedPartie = partieDAO.findLastByTournoi(selectedTournoi.getIdTournoi());
                    if(selectedPartie != null)
                     System.out.println(selectedPartie.getLabel());
                    else
                      System.out.println("null");
                }
                partieSelectModel = selectModelFactory.create(parties, "label");
            }
        }

    }

    public boolean isPartie() {
        return parties != null && parties.size() > 0;
    }

    public boolean isTournoi() {
        return tournois != null && tournois.size() > 0;
    }

    void onValueChangedFromTournoiSelection(TournoiEntity selectedTournoiChanged) {
        this.selectedTournoi = selectedTournoiChanged;
        this.selectedPartie = null;
        setupRender();
        ajaxResponseRenderer.addRender(selectTournoiZone);
        ajaxResponseRenderer.addCallback(new JavaScriptCallback() {
            public void run(JavaScriptSupport javaScriptSupport) {
                javaScriptSupport.addScript("document.location.reload(true)");
            }
        });
    }

    void onValueChangedFromPartieSelection(PartieEntity selectedPartieChanged) {
        this.selectedPartie = selectedPartieChanged;
        setupRender();
        ajaxResponseRenderer.addRender(selectTournoiZone);
        ajaxResponseRenderer.addCallback(new JavaScriptCallback() {
            public void run(JavaScriptSupport javaScriptSupport) {
                javaScriptSupport.addScript("document.location.reload(true)");
            }
        });
    }
}
