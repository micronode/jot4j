package org.mnode.jot.schema;

import java.awt.geom.Point2D;

/**
 * A geographical location.
 */
public interface Place extends Entity {

    Place coordinates(Point2D.Float coordinates);

    interface Provider {

        Place place(String uid);
    }
}
