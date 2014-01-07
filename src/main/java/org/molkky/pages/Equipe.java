package org.molkky.pages;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.SelectModelFactory;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.services.ajax.JavaScriptCallback;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.molkky.dao.EquipeDAO;
import org.molkky.dao.MembreDAO;
import org.molkky.entities.EquipeEntity;
import org.molkky.entities.MembreEntity;
import org.molkky.entities.PartieEntity;
import org.molkky.services.XLSAttachement;
import org.springframework.context.annotation.ImportResource;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 3/19/13
 * Time: 7:47 PM
 * To change this template use File | Settings | File Templates.
 */
@Import(library = {"context:static/js/select2-3.4.5/select2.js"}, stylesheet = {"context:static/js/select2-3.4.5/select2.css"})
public class Equipe {

    private int index;

    @Persist
    @Property
    private Integer numeroEquipe;

    @Property
    private Select membre1, membre2;

  /*  @Persist*/
    @Property
    private Integer selectedMembre1;

    @Property
    private Integer selectedMembre2;

    @Persist
    @Property
    private List<MembreEntity> membres1;

    @Persist
    @Property
    private List<MembreEntity> membres2;

    @Property
    @Persist
    private BeanModel<EquipeEntity> equipeModel;

    @Property
    @Persist
    private BeanModel<EquipeEntity> equipeRandomModel;

    @Property
    @Persist
    private SelectModel membre1SelectModel;

    @Property
    @Persist
    private SelectModel membre2SelectModel;

    @Inject
    private SelectModelFactory selectModelFactory;

    @Inject
    private BeanModelSource beanModelSource;

    @Property
    private EquipeEntity currentEquipe;

    @Inject
    private Messages messages;

    @Inject
    private MembreDAO membreDAO;

    @Inject
    private EquipeDAO equipeDAO;

    @Persist
    @Property
    private int numOfTerrain;

    @InjectComponent
    private Zone randomListZone, zone, membre2Zone;

    @Property
    private List<EquipeEntity> equipesList;

    @Component
    private Form addEquipeForm, paramRandomForm;

    @Property
    @Persist
    private List<List<EquipeEntity>> randomTerrainsList;

    @Property
    private List<EquipeEntity> randomEquipeList;

    @SessionState(create = false)
    @Property
    private PartieEntity selectedPartie;

    private boolean selectedPartieExists;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @Inject
    @Path("context:static/xls/template-partie.xls")
    private Asset templatePartie;

    void setupRender() {

        membres1 = new ArrayList<MembreEntity>();
        membres2 = new ArrayList<MembreEntity>();

        if (selectedPartieExists && selectedPartie != null) {
            numeroEquipe = equipeDAO.getMaxNumberByPartie(selectedPartie.getIdPartie()) + 1;
            membres1 = membreDAO.getAllWithoutEquipeByPartie(selectedPartie.getIdPartie());
            equipesList = equipeDAO.findAllByPartie(selectedPartie.getIdPartie());
        }


        membre1SelectModel = selectModelFactory.create(membres1, "label");

        membre2SelectModel = selectModelFactory.create(membres2, "label");

        equipeModel = beanModelSource.createDisplayModel(EquipeEntity.class, messages);
        equipeModel.add("membre1", null);
        equipeModel.add("membre2", null);
        equipeModel.add("delete", null);
        equipeModel.include("numeroEquipe", "membre1", "membre2", "delete");


        equipeRandomModel = beanModelSource.createDisplayModel(EquipeEntity.class, messages);
        equipeRandomModel.add("membre1", null).sortable(true);
        equipeRandomModel.add("membre2", null).sortable(true);

        equipeRandomModel.include("numeroEquipe", "membre1", "membre2");

    }

    void onValidateFromAddEquipeForm() {
        if (!selectedPartieExists || selectedPartie == null) {
            addEquipeForm.recordError(messages.get("noPartie"));
        } else {
            if (numeroEquipe == null) {
                addEquipeForm.recordError(messages.get("numberNull"));
            } else if (equipeDAO.checkNumber(selectedPartie.getIdPartie(), numeroEquipe)) {
                addEquipeForm.recordError(messages.get("numberExist"));
            }

        }

        if (selectedMembre1 == null || selectedMembre2 == null) {
            addEquipeForm.recordError(messages.get("noMembre"));
        } else if (selectedMembre1 == selectedMembre2) {
            addEquipeForm.recordError(messages.get("membreEquals"));
        }


    }

    void onValueChangedFromMembre1(Integer selectedMembre1) {
        if (selectedMembre1 != null) {
            membres2 = membreDAO.getAllWithoutEquipeByPartieAndNotAlreadySelected(selectedPartie.getIdPartie(), selectedMembre1);
        } else {
            membres2 = new ArrayList<MembreEntity>();
        }
        membre2SelectModel = selectModelFactory.create(membres2, "label");

        ajaxResponseRenderer.addRender(membre2Zone);
        ajaxResponseRenderer.addCallback(new JavaScriptCallback() {
            public void run(JavaScriptSupport javaScriptSupport) {
                javaScriptSupport.addScript("var id1 = document.getElementsByName(\"membre1\")[0].id; var id2 = document.getElementsByName(\"membre2\")[0].id;  $(\"#\"+id1).select2(); $(\"#\"+id2).select2();");
            }
        });

    }

    Object onActionFromRandomize() {

        if (numOfTerrain > 0) {
            List<EquipeEntity> equipesList2 = equipeDAO.findAllByPartie(selectedPartie.getIdPartie());
            int sizeOfList = (equipesList2.size() / numOfTerrain);

            int lower = 0;
            int higher = equipesList2.size();
            randomTerrainsList = new ArrayList<List<EquipeEntity>>();
            int i = 0;
            while (i < numOfTerrain) {
                List<EquipeEntity> group = new ArrayList<EquipeEntity>();

                randomTerrainsList.add(group);
                i++;
            }
            int indexOfList = 0;
            while (true) {

                int random = (int) (Math.random() * (higher - lower)) + lower;
                if (equipesList2.size() > 0) {
                    randomTerrainsList.get(indexOfList).add(equipesList2.get(random));
                    equipesList2.remove(random);
                    higher = equipesList2.size();

                } else {
                    break;
                }
                indexOfList++;
                if (indexOfList == numOfTerrain)
                    indexOfList = 0;
            }
        } else {
            randomTerrainsList = new ArrayList<List<EquipeEntity>>();
        }

        return randomListZone;

    }

    void onSuccessFromAddEquipeForm() {

        equipeDAO.save(new EquipeEntity(numeroEquipe, selectedMembre1, selectedMembre2, selectedPartie.getIdPartie()));


    }

    public JSONObject getParams() {
        JSONObject options = new JSONObject();
        options.put("title", "Groupe");
        options.put("width", 700);
        options.put("height", 600);
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", true);
        return options;
    }

    @Inject
    private AlertManager alertManager;

    void onActionFromDelete(int id) {
        EquipeEntity equipe = equipeDAO.findById(id);
        try{
        equipeDAO.delete(equipe);
        }catch(Exception e){
            alertManager.alert(Duration.SINGLE, Severity.ERROR, "Vous ne pouvez pas supprimer cette equipe une erreur est survenue.");

        }
    }

    public int getIndex() {
        return index + 1;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public StreamResponse onActionFromTelechargerXLS(){
      HSSFSheet sheet;
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(templatePartie.getResource().openStream());

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if (workbook == null) {
            workbook = new HSSFWorkbook();
            workbook.createSheet("sheet1");
        } else {

            for(int i = 0; i<randomTerrainsList.size()-1;i++)
            {
            workbook.cloneSheet(0);
            workbook.setSheetName(i, "Terrain "+(i+1));
            }
            workbook.setSheetName(randomTerrainsList.size()-1, "Terrain "+randomTerrainsList.size());

            for(int i = 0; i<randomTerrainsList.size();i++)
            {
            sheet = workbook.getSheetAt(i);
            List<EquipeEntity> equipeEntities = randomTerrainsList.get(i);
            int rownum = 0;
                Row row = sheet.getRow(rownum++);
                Row row2 = sheet.getRow(rownum++);
            int cellnum = 0;
            for (EquipeEntity equipe : equipeEntities) {

                Cell cell = row.getCell(cellnum++);
                cell.setCellValue("Equipe"+equipe.getNumeroEquipe());
                Cell cell2 = row2.getCell(cellnum-1);
                cell2.setCellValue(equipe.getMembre1().getLabel());

                cellnum++;
                Cell cell3 = row2.getCell(cellnum-1);
                cell3.setCellValue(equipe.getMembre2().getLabel());
            }
            }
        }



        try {
            InputStream stream = null;
            OutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            out.close();

            InputStream decodedInput = new ByteArrayInputStream(((ByteArrayOutputStream) out).toByteArray());
            System.out.println("Excel written successfully..");
            return new XLSAttachement(decodedInput, "test");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}