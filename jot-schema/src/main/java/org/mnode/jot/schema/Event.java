package org.mnode.jot.schema;

import java.time.ZonedDateTime;

/**
 * Defines a time-based jot with optional duration and location.
 */
public interface Event extends Jot {

    Event startTime(ZonedDateTime startTime);

    Event endTime(ZonedDateTime endTime);

    interface Provider {

        Event event(String uid);
    }
}
