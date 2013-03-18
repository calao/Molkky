package org.molkky.services;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ValueEncoderFactory;
import org.molkky.dao.TournoiDAO;
import org.molkky.entities.TournoiEntity;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 3/18/13
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class TournoiEncoder implements ValueEncoder<TournoiEntity>, ValueEncoderFactory<TournoiEntity> {

    @Inject
    private TournoiDAO tournoiDao;

    public String toClient(TournoiEntity value) {
        // return the given object's ID
        return String.valueOf(value.getIdTournoi());
    }

    public TournoiEntity toValue(String id) {
        // find the color object of the given ID in the database
        return tournoiDao.findById(Integer.parseInt(id));
    }

    public ValueEncoder<TournoiEntity> create(Class<TournoiEntity> type) {
        return this;
    }
}
