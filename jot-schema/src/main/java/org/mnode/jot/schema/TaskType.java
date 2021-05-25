package org.mnode.jot.schema;

/**
 * A classification of tasks (e.g. assessment, job, review, etc.).
 */
public interface TaskType extends Entity {

    interface Provider {

        TaskType taskType(String uid);
    }
}
