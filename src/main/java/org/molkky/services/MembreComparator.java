package org.molkky.services;

import org.molkky.entities.MembreEntity;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 1/2/14
 * Time: 5:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class MembreComparator implements Comparator<MembreEntity> {

    @Override
    public int compare(MembreEntity o1, MembreEntity o2) {
        int length = 0;
        if (o1 != null && o2 != null) {
            if (o1.getLabel().length() > o2.getLabel().length()) {
                length = o2.getLabel().length();
            } else {
                length = o1.getLabel().length();
            }
            for (int i = 0; i < length; i++) {
                char o1Char = o1.getLabel().toLowerCase().charAt(i);
                char o2Char = o2.getLabel().toLowerCase().charAt(i);

                if (o1Char == o2Char) {
                    continue;
                } else if (o1Char > o2Char) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
