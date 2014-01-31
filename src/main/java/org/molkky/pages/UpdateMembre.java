package org.molkky.pages;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.molkky.dao.MembreDAO;
import org.molkky.entities.MembreEntity;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 1/31/14
 * Time: 5:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class UpdateMembre {
    @Property
    @Persist
    private MembreEntity selectedMembreToUpdate;

    @Component(id ="UpdateMembreForm")
    private BeanEditForm updateMembreForm;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String success;

    @Inject
    private MembreDAO membreDAO;

    void setupRender() {
        if (selectedMembreToUpdate == null) {
            selectedMembreToUpdate = membreDAO.findById(0);
        }

    }

    void onActivate(Integer membreId) {
        System.out.println("membre " + membreId);
        selectedMembreToUpdate = membreDAO.findById(membreId);
    }

    void onValidateFromUpdateMembreForm() {
        if(selectedMembreToUpdate.getPrenom() == null || selectedMembreToUpdate.getPrenom().equals("") || selectedMembreToUpdate.getPrenom().replace(" ", "").equals(""))
        {
            updateMembreForm.recordError("Le prénom est obligatoire");
        }

        if(selectedMembreToUpdate.getNom() == null || selectedMembreToUpdate.getNom().equals("") || selectedMembreToUpdate.getNom().replace(" ", "").equals(""))
        {
            updateMembreForm.recordError("Le Nom est obligatoire");
        }


    }

    void onSuccessFromUpdateMembreForm() {
        membreDAO.saveOrUpdate(selectedMembreToUpdate);
        success = "Le membre " + selectedMembreToUpdate.getNom() + " " + selectedMembreToUpdate.getPrenom() + " a été mis à jour avec succés.";
    }
}