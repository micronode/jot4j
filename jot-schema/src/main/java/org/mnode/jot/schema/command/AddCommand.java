package org.mnode.jot.schema.command;

/**
 * Add the specified entity/document.
 * @param <T>
 */
public interface AddCommand<T, R> {

    R add(T input);
}
