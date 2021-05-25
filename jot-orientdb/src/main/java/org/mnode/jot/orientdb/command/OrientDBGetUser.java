package org.mnode.jot.orientdb.command;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.record.OVertex;
import org.mnode.jot.schema.command.GetCommand;

import java.util.List;

public class OrientDBGetUser extends AbstractOrientDBCommand implements GetCommand<OVertex, String> {

    public OrientDBGetUser(ODatabaseDocument session) {
        super(session);
    }

    @Override
    public List<OVertex> get(String input) {
        return getVertices("SELECT FROM User WHERE name = ?", input);
    }
}
