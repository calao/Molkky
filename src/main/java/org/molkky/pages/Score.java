package org.molkky.pages;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.SelectModelFactory;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.services.ajax.JavaScriptCallback;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.components.InPlaceEditor;
import org.molkky.dao.EquipeDAO;
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
@Import(library = {"context:static/js/select2-3.4.5/select2.js"}, stylesheet = {"context:static/js/select2-3.4.5/select2.css"})
public class Score {

    @InjectComponent
    private Zone numeroEquipeZone;

    @Property
    @Persist
    private List<MembreEntity> membresList;

    @SessionState(create = false)
    private PartieEntity selectedPartie;

    @Inject
    private SelectModelFactory selectModelFactory;

    @Property
    @Persist
    private List<Integer> numberOfEquipeModel;

    @Property
    private Integer pointMembre1, pointMembre2;

    @Persist
    @Property
    private String label1, label2;

    @Property
    @Persist
    private Integer selectedNumeroEquipe;

    @Inject
    private MembreDAO membreDao;

    @Inject
    private EquipeDAO equipeDAO;

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

        numberOfEquipeModel = new ArrayList<Integer>();

        if(selectedPartie!=null)
        {
        numberOfEquipeModel = equipeDAO.getAllEquipeNumberByPartie( selectedPartie.getIdPartie());
        }

    }

    private Integer getScoreManche(int manche)
    {
        ScoreEntity score = scoreDAO.getScoreByMancheAndMemberAndPartie(manche, currentMembre.getIdMembre(), selectedPartie.getIdPartie());
        if (score == null)
        {
          return null;
        }
        return score.getScore();
    }

    public Integer getScorePartie1() {
        return getScoreManche(1);
    }

    public void setScorePartie1(int scorePartie1) {
        this.scorePartie1 = scorePartie1;
    }

    public Integer getScorePartie2() {
        return getScoreManche(2);
    }

    public void setScorePartie2(int scorePartie2) {
        this.scorePartie2 = scorePartie2;
    }

    public Integer getScorePartie3() {
        return getScoreManche(3);
    }

    public void setScorePartie3(int scorePartie3) {
        this.scorePartie3 = scorePartie3;
    }

    public Integer getScorePartie4() {
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
        label1 = null;
        label2 =null;
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

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    void onValueChangedFromNumeroEquipe(Integer selectedNumeroEquipe) {
        this.selectedNumeroEquipe = selectedNumeroEquipe;
        List<MembreEntity> membres = membreDao.getAllByPartieAndEquipe(selectedPartie.getIdPartie(), selectedNumeroEquipe);
        label1 = membres.get(0).getLabel();
        label2 = membres.get(1).getLabel();
        ajaxResponseRenderer.addRender(numeroEquipeZone);
        ajaxResponseRenderer.addCallback(new JavaScriptCallback() {
            public void run(JavaScriptSupport javaScriptSupport) {
                javaScriptSupport.addScript("var id1 = document.getElementsByName(\"numeroEquipe\")[0].id; $(\"#\"+id1).select2();");
            }
        });

    }



}