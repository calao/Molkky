package org.molkky.pages;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.molkky.dao.ScoreDAO;
import org.molkky.entities.MembreEntity;
import org.molkky.entities.PartieEntity;
import org.molkky.entities.ScoresPartiesviewEntity;
import org.molkky.entities.ScoresTournoiViewEntity;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 10/16/13
 * Time: 3:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Classement {

    @Property
    @Persist
    private List<ScoresPartiesviewEntity> membresList;

    @Property
    @Persist
    private List<ScoresTournoiViewEntity> membresTournoiList;

    @SessionState(create = false)
    private PartieEntity selectedPartie;

    @Inject
    private ScoreDAO scoreDAO;

    @Persist
    @Property
    private BeanModel<ScoresPartiesviewEntity> membreModel;

    @Persist
    @Property
    private BeanModel<ScoresTournoiViewEntity> membreTournoiModel;


    private Integer currentIndex = 1;
    private Integer currentIndexTournoi = 1;

    @Property
    private Boolean classementParPartie = true;

    @Property
    private ScoresPartiesviewEntity currentMembre;

    @Property
    private ScoresTournoiViewEntity currentMembreTournoi;

    @Inject
    private BeanModelSource beanModelSource;

    private int idMembre = 0;


    private int scorePartie1, scorePartie2, scorePartie3, scorePartie4;

    @Inject
    private Messages messages;

    @Inject
    private ComponentResources _componentResources;

    @InjectComponent
    private Zone classementZone;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    void setupRender() {
        if (selectedPartie != null) {
           membresList = scoreDAO.getClassementByPartie(selectedPartie.getIdPartie());
           membresTournoiList = scoreDAO.getClassementByTournoi(selectedPartie.getIdTournoi());
        }

        if (membreModel == null) {
            membreModel = beanModelSource.createDisplayModel(ScoresPartiesviewEntity.class, messages);
            membreModel.add("place", null);
            membreModel.add("id.score");
            membreModel.add("id.nom");
            membreModel.add("id.prenom");
            membreModel.include("place", "id.nom","id.prenom", "id.score");
        }
        if (membreTournoiModel == null) {
            membreTournoiModel = beanModelSource.createDisplayModel(ScoresTournoiViewEntity.class, messages);
            membreTournoiModel.add("place", null);
            membreTournoiModel.add("id.score");
            membreTournoiModel.add("id.nom");
            membreTournoiModel.add("id.prenom");
            membreTournoiModel.include("place", "id.nom","id.prenom", "id.score");
        }

    }

    public Integer getCurrentIndex() {
        return currentIndex++;
    }

    public void setCurrentIndex(Integer currentIndex) {
        this.currentIndex = currentIndex;
    }

    public Integer getCurrentIndexTournoi() {
        return currentIndexTournoi++;
    }

    public void setCurrentIndexTournoi(Integer currentIndexTournoi) {
        this.currentIndexTournoi = currentIndexTournoi;
    }

    void onActionFromClassementParPartie()
    {
        classementParPartie = true;
        currentIndex = 1;
        currentIndexTournoi = 1;
        ajaxResponseRenderer.addRender(classementZone);

    }
    void onActionFromClassementParTournoi()
    {
        classementParPartie = false;
        currentIndex = 1;
        currentIndexTournoi = 1;
        ajaxResponseRenderer.addRender(classementZone);
    }
}
