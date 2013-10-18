package org.molkky.pages;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.BeanModelSource;
import org.got5.tapestry5.jquery.components.InPlaceEditor;
import org.molkky.dao.MembreDAO;
import org.molkky.dao.ScoreDAO;
import org.molkky.entities.MembreEntity;
import org.molkky.entities.PartieEntity;
import org.molkky.entities.ScoreEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 4/9/13
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Score {

    @Property
    @Persist
    private List<MembreEntity> membresList;

    @SessionState(create = false)
    private PartieEntity selectedPartie;

    @Inject
    private MembreDAO membreDao;

    @Inject
    private ScoreDAO scoreDAO;

    @Persist
    @Property
    private BeanModel<MembreEntity> membreModel;

    @Property
    private Integer currentIndex = 0;

    @Property
    private MembreEntity currentMembre;

    @Inject
    private BeanModelSource beanModelSource;

    private int idMembre = 0;


    private int scorePartie1, scorePartie2, scorePartie3, scorePartie4;

    @Inject
    private Messages messages;

    @Inject
    private ComponentResources _componentResources;

    void setupRender() {
        if (selectedPartie != null) {
            membresList = membreDao.getAllByPartie(selectedPartie.getIdPartie());

        }

        if (membresList == null) {
            membresList = new ArrayList<MembreEntity>();
        }

        if (membreModel == null) {
            membreModel = beanModelSource.createDisplayModel(MembreEntity.class, messages);
            membreModel.add("manche1", null);
            membreModel.add("manche2", null);
            membreModel.add("manche3", null);
            membreModel.add("manche4", null);
            membreModel.include("nom", "prenom", "manche1", "manche2", "manche3", "manche4");
        }

    }

    private int getScoreManche(int manche)
    {
        ScoreEntity score = scoreDAO.getScoreByMancheAndMemberAndPartie(manche, currentMembre.getIdMembre(), selectedPartie.getIdPartie());
        if (score == null)
        { score = new ScoreEntity(manche, currentMembre.getIdMembre(), selectedPartie.getIdPartie(), 0);
          scoreDAO.save(score);
          return score.getScore();
        }
        return score.getScore();
    }

    public int getScorePartie1() {
        return getScoreManche(1);
    }

    public void setScorePartie1(int scorePartie1) {
        this.scorePartie1 = scorePartie1;
    }

    public int getScorePartie2() {
        return getScoreManche(2);
    }

    public void setScorePartie2(int scorePartie2) {
        this.scorePartie2 = scorePartie2;
    }

    public int getScorePartie3() {
        return getScoreManche(3);
    }

    public void setScorePartie3(int scorePartie3) {
        this.scorePartie3 = scorePartie3;
    }

    public int getScorePartie4() {
        return getScoreManche(4);
    }

    public void setScorePartie4(int scorePartie4) {
        this.scorePartie4 = scorePartie4;
    }

    public int getIdMembre() {
        if (idMembre == 0)
            return currentMembre.getIdMembre();
        else
            return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }


    @OnEvent(component = "scorePartie1", value = InPlaceEditor.SAVE_EVENT)
    void setScorePartie1(int idMembre, int value) {
        setScoreManche(1, idMembre, value);
        System.out.println("id " + idMembre + " valeur " +value);
    }

    private void setScoreManche(int manche, int idMembre, int value)
    {
        ScoreEntity score = scoreDAO.getScoreByMancheAndMemberAndPartie(manche, idMembre, selectedPartie.getIdPartie());
        if(score != null)
        {
            score.setScore(value);
            scoreDAO.update(score);
        }
        else
        {
            score = new ScoreEntity(manche, idMembre, selectedPartie.getIdPartie(), value);
            scoreDAO.save(score);
        }

    }

    @OnEvent(component = "scorePartie2", value = InPlaceEditor.SAVE_EVENT)
    void setScorePartie2(int idMembre,  int value) {
        setScoreManche(2, idMembre, value);
        System.out.println("id " + idMembre + " valeur " +value);
    }

    @OnEvent(component = "scorePartie3", value = InPlaceEditor.SAVE_EVENT)
    void setScorePartie3(int idMembre, int value) {
        setScoreManche(3, idMembre, value);
        System.out.println("id " + idMembre + " valeur " +value);
    }

    @OnEvent(component = "scorePartie4", value = InPlaceEditor.SAVE_EVENT)
    void setScorePartie4(int idMembre, int value) {
        setScoreManche(4, idMembre, value);
        System.out.println("id " + idMembre + " valeur " +value);
    }


    /**
     * <p>
     * JSON parameter used to configure InPlaceEditor callback
     * </p>
     */
    public JSONObject getOptionsJSON() {
        JSONObject params = new JSONObject();
        params.put("tooltip", "Cliquer pour éditer");
        params.put("cancel", "Annuler");
        params.put("submit", "OK");
        params.put("width", "20");
        params.put("onblur","submit");
        return params;
    }

    public String getRowClass(){
        if(currentIndex%2 > 0)
        {
         return "even";
        }else{
          return "odd";
        }
    }


}