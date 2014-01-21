package org.molkky.pages;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Form;
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

    @Component
    private Form encodeScoreForm;

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

    @Persist
    @Property
    private Integer pointMembre1, pointMembre2;

    @Persist
    @Property(read = true)
    private MembreEntity membre1, membre2;

    @Property
    @Persist
    private Integer selectedNumeroEquipe, selectedNumeroManche;

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

        membre1 = null;
        membre2= null;
        selectedNumeroEquipe = null;

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
        return getScoreManche(manche, null);
    }

    private Integer getScoreManche(int manche, Integer idMembre)
    {
        ScoreEntity score = scoreDAO.getScoreByMancheAndMemberAndPartie(manche, idMembre==null?currentMembre.getIdMembre():idMembre, selectedPartie.getIdPartie());
        if (score == null)
        {
            return null;
        }
        return score.getScore();
    }

    public Integer getEquipe() {
        return getScoreManche(2);
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


/*    @OnEvent(component = "scorePartie1", value = InPlaceEditor.SAVE_EVENT)*/
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

    /*@OnEvent(component = "scorePartie2", value = InPlaceEditor.SAVE_EVENT)*/
    void setScorePartie2(int idMembre,  int value) {
        setScoreManche(2, idMembre, value);
        System.out.println("id " + idMembre + " valeur " +value);
    }

/*    @OnEvent(component = "scorePartie3", value = InPlaceEditor.SAVE_EVENT)*/
    void setScorePartie3(int idMembre, int value) {
        setScoreManche(3, idMembre, value);
        System.out.println("id " + idMembre + " valeur " +value);
    }

/*    @OnEvent(component = "scorePartie4", value = InPlaceEditor.SAVE_EVENT)*/
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
        membre1 = membres.get(0);
        membre2 = membres.get(1);
        pointMembre1 = getScoreManche(selectedNumeroManche, membre1.getIdMembre());
        pointMembre2 = getScoreManche(selectedNumeroManche, membre2.getIdMembre());
        ajaxResponseRenderer.addRender(numeroEquipeZone);
        regenSelectJS(ajaxResponseRenderer);
    }

    void onValueChangedFromNumeroManche(String selectedNumeroManche) {
        this.selectedNumeroManche = Integer.valueOf(selectedNumeroManche);

        ajaxResponseRenderer.addRender(numeroEquipeZone);
        regenSelectJS(ajaxResponseRenderer);
    }

    void regenSelectJS(AjaxResponseRenderer ajaxResponseRenderer)
    {
        ajaxResponseRenderer.addCallback(new JavaScriptCallback() {
            public void run(JavaScriptSupport javaScriptSupport) {
                javaScriptSupport.addScript("var id1 = document.getElementsByName(\"numeroEquipe\")[0].id; $(\"#\"+id1).select2();  var id2 = document.getElementsByName(\"numeroManche\")[0].id;\n" +
                        "            $(\"#\" + id2).select2();");
            }
        });
    }

    void onValidateFromEncodeScoreForm(){

        boolean error = false;
            if(pointMembre1!=pointMembre2 && pointMembre1 > 0 && pointMembre2 > 0 )
            {
             error = true;
             encodeScoreForm.recordError("Les point doivent être les même ou égal à 0");
            }

            if(pointMembre1<0 || pointMembre2 < 0 )
            {   error = true;
                encodeScoreForm.recordError("Les point ne peuvent pas être négatif");
            }

        if(error)
        {
            ajaxResponseRenderer.addRender(numeroEquipeZone);
        }
    }

     Object onSuccessFromEncodeScoreForm(){

        System.out.println("selectedNumeroManche " + selectedNumeroManche);
        System.out.println("membre2.getIdMembre() " +membre2.getIdMembre() );
        System.out.println("membre1.getIdMembre() " +membre1.getIdMembre());
        System.out.println("pointMembre1 "+pointMembre1);

        setScoreManche(selectedNumeroManche, membre1.getIdMembre(), pointMembre1);
        setScoreManche(selectedNumeroManche, membre2.getIdMembre(), pointMembre2);
        return this.getClass();
    }



}