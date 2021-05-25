package org.mnode.jot.schema;

/**
 * An {@link Entity} representing an individual.
 */
public interface Person extends Entity {

    Person emailAddress(String emailAddress);

    interface Provider {

        Person person(String uid);
    }
}
