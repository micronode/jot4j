package org.mnode.jot.schema;

/**
 * Represents a persistent entity associated with one or more jots.
 */
public interface Entity extends PropertyAccessor {

    <T extends Entity> T name(String name);
}
