package org.mnode.jot.schema;

/**
 * A group of {@link Jot}s used to collect opinions and preferences from individuals.
 *
 * @param <T> a jot sub-type used to limit the possible poll options.
 */
public interface Poll<T extends Jot> extends Jot {

    Poll<T> option(T option);

    interface Provider {

        <T extends Jot> Poll<T> poll(String uid);
    }
}
