package org.mnode.jot.schema;

/**
 * A specialised label used for tagging jots hierarchically.
 */
public interface Folder extends Label {

    Folder parent(Folder folder);

    Folder child(Folder folder);

    interface Provider {

        Folder folder(String uid);
    }
}
