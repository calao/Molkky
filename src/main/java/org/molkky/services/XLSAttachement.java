package org.molkky.services;

/**
 * Created with IntelliJ IDEA.
 * User: darksidious
 * Date: 10/18/13
 * Time: 10:48 AM
 * To change this template use File | Settings | File Templates.
 */

import java.io.InputStream;

public class XLSAttachement extends AttachmentStreamResponse {

    public XLSAttachement(InputStream is, String filename) {
        super(is, filename);
        this.contentType = "application/vnd.ms-excel";
        this.extension = "xls";
    }

    public XLSAttachement(InputStream is) {
        super(is);
        this.contentType = "application/vnd.ms-excel";
        this.extension = "xls";
    }

}
