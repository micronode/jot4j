package org.mnode.jot.schema;

import java.awt.*;

/**
 * A tag used to classify jots.
 */
public interface Label extends Entity {

    <T extends Label> T color(Color color);

    interface Provider {

        Label label(String uid);
    }
}
