package org.mnode.jot.schema.model;

import java.util.ArrayList;
import java.util.List;

public class SchemaClass {

    private String name;

    private String[] superclasses;

    private boolean isAbstract;

    private List<SchemaProperty> properties = new ArrayList<>();

    private List<SchemaLinkProperty> linkProperties = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSuperclasses() {
        return superclasses;
    }

    public void setSuperclasses(String[] superclasses) {
        this.superclasses = superclasses;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public List<SchemaProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<SchemaProperty> properties) {
        this.properties = properties;
    }

    public List<SchemaLinkProperty> getLinkProperties() {
        return linkProperties;
    }

    public void setLinkProperties(List<SchemaLinkProperty> linkProperties) {
        this.linkProperties = linkProperties;
    }
}
