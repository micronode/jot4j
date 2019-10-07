package org.mnode.jot.schema;

/**
 * Records the outcome of a past {@link Meeting}.
 */
public interface Minutes extends Jot<Minutes> {

    Minutes meeting(Meeting meeting);

    Minutes absentee(Person absentee);

    Minutes action(Task task);

    interface Provider {

        Minutes minutes(String uid);
    }
}
