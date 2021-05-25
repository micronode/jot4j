package org.mnode.jot.schema.command;

/**
 * Update the specified entity/document.
 * @param <T>
 */
public interface UpdateCommand<T, R> {

    R update(T input);
}
