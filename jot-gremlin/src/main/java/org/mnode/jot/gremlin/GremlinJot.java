package org.mnode.jot.gremlin;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.mnode.jot.data.AbstractJot;
import org.mnode.jot.schema.Label;
import org.mnode.jot.schema.Person;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.function.Supplier;

import static org.mnode.jot.schema.PropertyAccessor.Property.Summary;
import static org.mnode.jot.schema.PropertyAccessor.Property.Uid;

public class GremlinJot extends AbstractJot<GraphTraversal<Vertex, Vertex>> {

    public GremlinJot(Supplier<GraphTraversal<Vertex, Vertex>> supplier) {
        super(supplier);
    }

    @Override
    public GremlinJot summary(String summary) {
        supplier.get().property("summary", summary);
        return this;
    }

    @Override
    public GremlinJot authoredBy(Person... authors) {
        Arrays.stream(authors).forEach(user -> {
            supplier.get().addE("AuthoredBy").to((String) user.getProperty(Uid));
        });
        return this;
    }

    @Override
    public GremlinJot taggedWith(Label... labels) {
        Arrays.stream(labels).forEach(label -> {
            supplier.get().addE("TaggedWith").to((String) label.getProperty(Uid));
        });
        return this;
    }

    @Override
    public boolean isSupported(Property property) {
        return EnumSet.of(Summary).contains(property);
    }

    @Override
    public <V> V getProperty(Property property) {
        switch (property) {
            case Summary:
                return (V) supplier.get().next().property("summary").value();
        }
        throw new IllegalArgumentException(String.format("Unsupported property: %s", property));
    }
}
