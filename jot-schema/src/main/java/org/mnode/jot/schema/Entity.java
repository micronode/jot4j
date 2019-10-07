package org.mnode.jot.schema;

/**
 * Represents a persistent entity associated with one or more jots.
 *
 * @param <T> a sub-type that defines specialised types of entities
 */
public interface Entity<T extends Entity> extends PropertyAccessor {

    T name(String name);
}
