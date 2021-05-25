package org.mnode.jot.schema;

import java.time.ZonedDateTime;

/**
 * Describes an action with an optional target date for completion.
 */
public interface Task extends Jot {

    enum CompletionStatus { NOT_STARTED, IN_PROGRESS, COMPLETED }

    Task taskType(TaskType taskType);

    Task project(Project project);

    Task dueDate(ZonedDateTime dueDate);

    Task status(CompletionStatus completionStatus);

    Task attachments(ExternalLink... attachments);

    interface Provider {

        Task task(String uid);
    }
}
