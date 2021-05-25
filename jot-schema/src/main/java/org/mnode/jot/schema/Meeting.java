package org.mnode.jot.schema;

/**
 * A specialised event that captures a gathering of people.
 */
public interface Meeting extends Event {

    Meeting attendee(Person attendee);

    Meeting organizer(Person organizer);

    interface Provider {

        Meeting meeting(String uid);
    }
}
