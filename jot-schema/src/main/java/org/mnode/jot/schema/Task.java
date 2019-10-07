package org.mnode.jot.schema;

import java.time.ZonedDateTime;

/**
 * Describes an action with an optional target date for completion.
 */
public interface Task extends Jot<Task> {

    enum CompletionStatus { NOT_STARTED, IN_PROGRESS, COMPLETED }

    Task dueDate(ZonedDateTime dueDate);

    Task completionStatus(CompletionStatus completionStatus);

    interface Provider {

        Task task(String uid);
    }
}
