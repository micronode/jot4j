package org.mnode.jot.orientdb;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import org.mnode.jot.orientdb.command.OrientDBAddSchemaClass;
import org.mnode.jot.orientdb.parser.SchemaClass;

import java.util.List;

public class OrientDBSchemaController implements SchemaController {

    private final ODatabaseDocument session;

    public OrientDBSchemaController(ODatabaseDocument session) {
        this.session = session;
    }

    @Override
    public void updateSchema(List<SchemaClass> classes) {
        OrientDBAddSchemaClass command = new OrientDBAddSchemaClass(session);
        classes.forEach(command::add);
    }
}
