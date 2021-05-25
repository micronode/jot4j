package org.mnode.jot.orientdb;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import org.mnode.jot.orientdb.command.OrientDBAddTextNote;
import org.mnode.jot.orientdb.command.OrientDBUpdateTextNote;
import org.mnode.jot.schema.TextNote;
import org.mnode.jot.schema.command.NodeController;

import java.util.List;

public class OrientDBTextNoteController implements NodeController<TextNote, String> {

    private final ODatabaseDocument session;

    public OrientDBTextNoteController(ODatabaseDocument session) {
        this.session = session;
    }

    @Override
    public boolean add(TextNote input) {
        return new OrientDBAddTextNote(session).add(input) != null;
    }

    @Override
    public List<TextNote> get(String query) {
        return null;
    }

    @Override
    public List<TextNote> remove(String query) {
        return null;
    }

    @Override
    public boolean update(TextNote input) {
        return new OrientDBUpdateTextNote(session).update(input) != null;
    }
}
