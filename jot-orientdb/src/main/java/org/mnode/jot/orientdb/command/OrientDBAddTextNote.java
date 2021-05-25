package org.mnode.jot.orientdb.command;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.record.OVertex;
import org.mnode.jot.orientdb.mapper.TextNoteMapper;
import org.mnode.jot.schema.PropertyAccessor;
import org.mnode.jot.schema.TextNote;
import org.mnode.jot.schema.command.AddCommand;
import org.mnode.jot.schema.command.NodeType;

public class OrientDBAddTextNote extends AbstractOrientDBVertexCommand<TextNote> implements AddCommand<TextNote, OVertex> {

    public OrientDBAddTextNote(ODatabaseDocument session) {
        super(session, NodeType.TextNote, new TextNoteMapper());
    }

    @Override
    public OVertex add(TextNote input) {
        OVertex vertex = createVertex();
        getMapper().map(input, vertex);
        new OrientDBAuthoredBy(getSession()).update(vertex, input.getProperty(PropertyAccessor.Property.Author));
        return updateVertex(vertex);
    }
}
