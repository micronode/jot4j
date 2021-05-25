package org.mnode.jot.orientdb.command;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.record.OVertex;
import org.mnode.jot.orientdb.mapper.TextNoteMapper;
import org.mnode.jot.schema.TextNote;
import org.mnode.jot.schema.command.NodeType;
import org.mnode.jot.schema.command.UpdateCommand;

import static org.mnode.jot.schema.PropertyAccessor.Property.Author;
import static org.mnode.jot.schema.PropertyAccessor.Property.Uid;

public class OrientDBUpdateTextNote extends AbstractOrientDBVertexCommand<TextNote>
        implements UpdateCommand<TextNote, OVertex> {

    public OrientDBUpdateTextNote(ODatabaseDocument session) {
        super(session, NodeType.TextNote, new TextNoteMapper());
    }

    @Override
    public OVertex update(TextNote input) {
        OVertex vertex = retrieveVertex(input.getProperty(Uid));
        getMapper().map(input, vertex);
        new OrientDBAuthoredBy(getSession()).update(vertex, input.getProperty(Author));
        return updateVertex(vertex);
    }
}
