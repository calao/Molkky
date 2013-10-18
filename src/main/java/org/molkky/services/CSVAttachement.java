package org.molkky.services;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 10/18/13
 * Time: 10:48 AM
 * To change this template use File | Settings | File Templates.
 */

import java.io.InputStream;

public class CSVAttachement extends AttachmentStreamResponse {

    public CSVAttachement(InputStream is, String args) {
        super(is, args);
        this.contentType = "application/CSV";
        this.extension = "csv";
    }

    public CSVAttachement(InputStream is) {
        super(is);
        this.contentType = "application/CSV";
        this.extension = "csv";
    }

}
