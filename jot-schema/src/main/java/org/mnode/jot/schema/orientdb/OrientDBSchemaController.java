package org.mnode.jot.schema.orientdb;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import org.mnode.jot.schema.SchemaController;
import org.mnode.jot.schema.model.SchemaClass;

import java.util.List;

public class OrientDBSchemaController implements SchemaController {

    private final ODatabaseDocument session;

    public OrientDBSchemaController(ODatabaseDocument session) {
        this.session = session;
    }

    @Override
    public void updateSchema(List<SchemaClass> classes) {
        OrientDBAddSchemaClass command = new OrientDBAddSchemaClass(session);
        classes.stream().forEach(schemaClass -> command.add(schemaClass));
    }
}
