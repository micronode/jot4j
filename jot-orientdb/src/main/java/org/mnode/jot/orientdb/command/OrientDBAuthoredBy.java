package org.mnode.jot.orientdb.command;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.record.OEdge;
import com.orientechnologies.orient.core.record.OVertex;
import org.mnode.jot.schema.User;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.mnode.jot.schema.PropertyAccessor.Property.Name;
import static org.mnode.jot.schema.command.ArcType.AuthoredBy;

public class OrientDBAuthoredBy extends AbstractOrientDBCommand implements ArcCommand<List<User>> {

    public OrientDBAuthoredBy(ODatabaseDocument session) {
        super(session);
    }

    @Override
    public boolean update(OVertex vertex, List<User> authors) {
        boolean success = true;
        List<OVertex> existingAuthors = new ArrayList<>();

        List<OEdge> authorEdges = getEdges("SELECT from E WHERE out = ?", vertex);
        for (OEdge edge : authorEdges) {
            edge.setProperty("lastUpdate", Instant.now());
            existingAuthors.add(edge.getFrom());
            success &= getSession().save(edge).isDirty();
        }

        for (User author : authors) {
            OVertex authorNode = getUserElement(author);
            if (!existingAuthors.contains(authorNode)) {
                OEdge edge = getSession().newEdge(vertex, authorNode, AuthoredBy.toString());
                edge.setProperty("lastUpdate", Date.from(Instant.now()));
                success &= getSession().save(edge).isDirty();
            }
        }
        return success;
    }

    protected OVertex getUserElement(User user) {
        List<OVertex> results = getVertices("SELECT FROM User WHERE name = ?", user.getProperty(Name));
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            return new OrientDBAddUser(getSession()).add(user);
        }
    }
}
