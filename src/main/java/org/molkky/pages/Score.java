package org.molkky.pages;

import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.molkky.dao.MembreDAO;
import org.molkky.entities.MembreEntity;
import org.molkky.entities.PartieEntity;

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
    private List<MembreEntity> membresList;

    @SessionState(create = false)
    private PartieEntity selectedPartie;

    @Inject
    private MembreDAO membreDao;

    @Persist
    @Property
    private BeanModel<MembreEntity> membreModel;

    @Property
    private MembreEntity currentMembre;

    @Inject
    private BeanModelSource beanModelSource;

    private int idMembre = 0;


    private int scorePartie1, scorePartie2, scorePartie3, scorePartie4;

    @Inject
    private Messages messages;

    @Component
    private Form encodeScoreForm;

    @InjectComponent
    private Zone zoneData;

    void setupRender() {
        if (selectedPartie != null) {
            membresList = membreDao.getAllByPartie(selectedPartie.getIdPartie());

        }

        if (membresList == null) {
            membresList = new ArrayList<MembreEntity>();
        }

        if (membreModel == null) {
            membreModel = beanModelSource.createDisplayModel(MembreEntity.class, messages);
            membreModel.add("partie1", null);
            membreModel.add("partie2", null);
            membreModel.add("partie3", null);
            membreModel.add("partie4", null);
            membreModel.include("pseudonyme", "nom", "prenom", "partie1", "partie2", "partie3", "partie4");
        }

    }

    Object onSuccessFromEncodeScoreForm() {
        return zoneData;
    }


    public int getScorePartie1() {
        return scorePartie1;
    }

    public void setScorePartie1(int scorePartie1) {
        System.out.println(idMembre);
        this.scorePartie1 = scorePartie1;
    }

    public int getScorePartie2() {
        return scorePartie2;
    }

    public void setScorePartie2(int scorePartie2) {
        this.scorePartie2 = scorePartie2;
    }

    public int getScorePartie3() {
        return scorePartie3;
    }

    public void setScorePartie3(int scorePartie3) {
        this.scorePartie3 = scorePartie3;
    }

    public int getScorePartie4() {
        return scorePartie4;
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
}