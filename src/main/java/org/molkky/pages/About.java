package org.molkky.pages;

import org.apache.tapestry5.annotations.BeforeRenderTemplate;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.molkky.dao.TournoiDAO;
import org.molkky.entities.TournoiEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class About {

    @Inject
    private TournoiDAO tournoiDAO;

    @BeforeRenderTemplate
    public void test() {
        TournoiEntity tournoi = new TournoiEntity();
        tournoi.setIdTournoi(1);
        tournoi.setStartDate("01/02/2013");
        tournoi.setEndDate("02/11/2013");
        tournoiDAO.save(tournoi);

    }

}
