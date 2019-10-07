package org.mnode.jot.schema;

/**
 * Record text-based information.
 */
public interface TextNote extends Jot<TextNote> {

    TextNote note(String note);

    interface Provider {

        TextNote textNote(String uid);
    }
}
