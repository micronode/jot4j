package org.mnode.jot.orientdb;

import org.mnode.jot.orientdb.parser.SchemaClass;

import java.util.List;

public interface SchemaController {

    void updateSchema(List<SchemaClass> classes);
}
