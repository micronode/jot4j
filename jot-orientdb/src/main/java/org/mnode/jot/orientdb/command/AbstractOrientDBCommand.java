package org.mnode.jot.orientdb.command;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.record.OEdge;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.sql.executor.OResultSet;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractOrientDBCommand {

    private final ODatabaseDocument session;

    public AbstractOrientDBCommand(ODatabaseDocument session) {
        this.session = session;
    }

    public ODatabaseDocument getSession() {
        return session;
    }

    protected List<OVertex> getVertices(String query, Object... args) {
        OResultSet resultSet = getSession().query(query, args);
        return resultSet.vertexStream().collect(Collectors.toList());
    }

    protected List<OEdge> getEdges(String query, Object... args) {
        OResultSet resultSet = getSession().query(query, args);
        return resultSet.edgeStream().collect(Collectors.toList());
    }
}
