package org.mnode.jot.schema.command;

import java.util.List;

/**
 * Get the specified entity/document.
 * @param <Q> query input
 */
public interface GetCommand<R, Q> {

    List<R> get(Q input);
}
