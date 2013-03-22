package org.molkky.services;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ValueEncoderFactory;
import org.molkky.dao.PartieDAO;
import org.molkky.entities.PartieEntity;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 3/18/13
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class PartieEncoder implements ValueEncoder<PartieEntity>, ValueEncoderFactory<PartieEntity> {

    @Inject
    private PartieDAO partieDAO;

    public String toClient(PartieEntity value) {
        // return the given object's ID
        return String.valueOf(value.getIdPartie());
    }

    public PartieEntity toValue(String id) {
        // find the color object of the given ID in the database
        return partieDAO.findById(Integer.parseInt(id));
    }

    public ValueEncoder<PartieEntity> create(Class<PartieEntity> type) {
        return this;
    }
}
