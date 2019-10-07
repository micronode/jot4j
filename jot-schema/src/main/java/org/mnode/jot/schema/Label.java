package org.mnode.jot.schema;

import java.awt.*;

/**
 * A tag used to classify jots.
 *
 * @param <T> optional sub-type for specialised tags.
 */
public interface Label<T extends Label> extends Entity<T> {

    T colour(Color colour);

    interface Provider {

        Label label(String uid);
    }
}
