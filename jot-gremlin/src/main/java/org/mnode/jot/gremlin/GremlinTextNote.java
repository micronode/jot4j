package org.mnode.jot.gremlin;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.mnode.jot.schema.TextNote;

import java.util.EnumSet;
import java.util.function.Supplier;

import static org.mnode.jot.schema.PropertyAccessor.Property.Summary;

public class GremlinTextNote extends GremlinJot implements TextNote {

    public GremlinTextNote(Supplier<GraphTraversal<Vertex, Vertex>> supplier) {
        super(supplier);
    }

    @Override
    public GremlinTextNote note(String note) {
        supplier.get().property("note", note);
        return this;
    }

    @Override
    public boolean isSupported(Property property) {
        return super.isSupported(property)
                || EnumSet.of(Summary).contains(property);
    }

    @Override
    public <V> V getProperty(Property property) {
        switch (property) {
            case Summary:
                return (V) supplier.get().next().property("summary").value();
        }
        return super.getProperty(property);
    }
}
