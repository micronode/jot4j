package org.mnode.jot.schema;

import java.time.ZonedDateTime;

/**
 * Defines a time-based jot with optional duration and location.
 *
 * @param <T> a sub-type for specialised events
 */
public interface Event<T extends Event> extends Jot<T> {

    T startTime(ZonedDateTime startTime);

    T endTime(ZonedDateTime endTime);

    interface Provider {

        Event event(String uid);
    }
}
