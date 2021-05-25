package org.mnode.jot.orientdb.command;

import com.orientechnologies.orient.core.record.OVertex;

/**
 * Update the specified entity/document.
 * @param <T>
 */
public interface ArcCommand<T> {

    boolean update(OVertex from, T to);
}
