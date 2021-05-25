package org.mnode.jot.orientdb.command;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.record.OVertex;
import org.mnode.jot.schema.command.NodeType;
import org.mnode.jot.schema.command.PropertyMapper;

import java.util.List;

public class AbstractOrientDBVertexCommand<T> extends AbstractOrientDBCommand {

    private final NodeType nodeType;

    private final PropertyMapper<T, OVertex> mapper;

    public AbstractOrientDBVertexCommand(ODatabaseDocument session, NodeType nodeType, PropertyMapper<T, OVertex> mapper) {
        super(session);
        this.nodeType = nodeType;
        this.mapper = mapper;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public PropertyMapper<T, OVertex> getMapper() {
        return mapper;
    }

    protected OVertex createVertex() {
        return getSession().newVertex(nodeType.toString());
    }

    protected OVertex retrieveVertex(String uid) {
        List<OVertex> results = getVertices(String.format("SELECT FROM %s WHERE uid = ?", nodeType), uid);
        if (!results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }

    protected OVertex updateVertex(OVertex vertex) {
        return getSession().save(vertex);
    }

    protected void deleteVertex(String uid) {
        getSession().delete(retrieveVertex(uid).getIdentity());
    }
}
