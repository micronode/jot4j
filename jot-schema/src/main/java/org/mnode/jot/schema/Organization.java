package org.mnode.jot.schema;

/**
 * An {@link Entity} construct that represents a business or group of people with a particular purpose.
 */
public interface Organization extends Entity {

    interface Provider {

        Organization organization(String uid);
    }
}
