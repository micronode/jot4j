package org.mnode.jot.schema;

import org.mnode.jot.schema.model.SchemaClass;

import java.util.List;

public interface SchemaController {

    void updateSchema(List<SchemaClass> classes);
}
