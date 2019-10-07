package org.mnode.jot.schema;

/**
 * An {@link Entity} representing an individual.
 *
 * @param <T> optional sub-type for specialised type of people.
 */
public interface Person<T extends Person> extends Entity<T> {

    Person emailAddress(String emailAddress);

    interface Provider {

        Person person(String uid);
    }
}
