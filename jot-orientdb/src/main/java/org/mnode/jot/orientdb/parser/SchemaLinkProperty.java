package org.mnode.jot.orientdb.parser;

public class SchemaLinkProperty extends SchemaProperty {

    private String schemaClass;

    public SchemaLinkProperty() {
        // override default type..
        setType("LINK");
    }

    public String getSchemaClass() {
        return schemaClass;
    }

    public void setSchemaClass(String schemaClass) {
        this.schemaClass = schemaClass;
    }
}
