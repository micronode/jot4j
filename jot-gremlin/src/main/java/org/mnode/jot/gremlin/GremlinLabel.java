package org.mnode.jot.gremlin;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.mnode.jot.schema.Label;

import java.awt.*;
import java.util.EnumSet;

public class GremlinLabel extends GremlinEntity implements Label {

    public GremlinLabel(GraphTraversalSource traversalSource) {
        super(traversalSource);
    }

    public GremlinLabel(GraphTraversalSource traversalSource, String id) {
        super(traversalSource, id);
    }

    @Override
    public GremlinLabel color(Color color) {
        traversal = traversal.property("colour", color.toString());
        return this;
    }

    @Override
    public boolean isSupported(Property property) {
        return super.isSupported(property)
                || EnumSet.of(Property.Color).contains(property);
    }

    @Override
    public <V> V getProperty(Property property) {
        switch (property) {
            case Color:
                return (V) Color.decode((String) traversal.next().property("colour").value());
        }
        return super.getProperty(property);
    }
}
