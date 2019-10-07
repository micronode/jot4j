package org.mnode.jot.schema;

import java.net.URL;

/**
 * A pointer to an attachment that may be associated with other Jots (e.g. PDF document, image, etc).
 */
public interface Attachment extends Jot<Attachment> {

    Attachment url(URL url);

    interface Provider {

        Attachment attachment(String uid);
    }
}
