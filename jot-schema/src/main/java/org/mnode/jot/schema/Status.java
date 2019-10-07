package org.mnode.jot.schema;

/**
 * A specialised {@link Label} that supports an alternative name for contextual display purposes.
 * For example, you might have a status of <pre>MeetingCancelled</pre> that is applied to cancelled meetings, but
 * for display purposes might want to show: <pre>Cancelled</pre>.
 */
public interface Status extends Label<Status> {

    Status displayName(String displayName);

    interface Provider {

        Status status(String uid);
    }
}
