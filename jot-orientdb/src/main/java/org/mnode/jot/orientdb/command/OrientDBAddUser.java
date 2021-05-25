package org.mnode.jot.orientdb.command;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.record.OVertex;
import org.mnode.jot.orientdb.mapper.UserMapper;
import org.mnode.jot.schema.User;
import org.mnode.jot.schema.command.AddCommand;
import org.mnode.jot.schema.command.NodeType;

public class OrientDBAddUser extends AbstractOrientDBVertexCommand<User> implements AddCommand<User, OVertex> {

    public OrientDBAddUser(ODatabaseDocument session) {
        super(session, NodeType.User, new UserMapper());
    }

    @Override
    public OVertex add(User input) {
        OVertex vertex = createVertex();
        getMapper().map(input, vertex);
        return  updateVertex(vertex);
    }
}
