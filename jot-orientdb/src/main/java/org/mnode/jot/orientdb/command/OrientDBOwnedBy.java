package org.mnode.jot.orientdb.command;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.record.OVertex;
import org.mnode.jot.schema.Person;

import java.util.List;

public class OrientDBOwnedBy extends AbstractOrientDBCommand implements ArcCommand<List<Person>> {

    public OrientDBOwnedBy(ODatabaseDocument session) {
        super(session);
    }

    @Override
    public boolean update(OVertex jot, List<Person> entity) {
        return false;
    }
}
