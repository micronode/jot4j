package org.mnode.jot.schema;

/**
 * A specialised label that is owned by another entity (e.g. a person or organization).
 */
public interface Project extends Label {

    Project owner(Entity owner);

    interface Provider {

        Project project(String uid);
    }
}
