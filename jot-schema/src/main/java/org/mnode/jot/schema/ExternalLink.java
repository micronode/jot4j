package org.mnode.jot.schema;

import java.net.URL;

/**
 * A pointer to an external resource that may be associated with other Jots (e.g. PDF document, image, etc).
 */
public interface ExternalLink extends Jot {

    ExternalLink url(URL url);

    interface Provider {

        ExternalLink externalLink(String uid);
    }
}
