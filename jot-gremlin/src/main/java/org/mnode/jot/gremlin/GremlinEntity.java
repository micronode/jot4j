package org.mnode.jot.gremlin;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.mnode.jot.schema.Entity;

import java.util.EnumSet;

import static org.mnode.jot.schema.PropertyAccessor.Property.Name;
import static org.mnode.jot.schema.PropertyAccessor.Property.Uid;

public class GremlinEntity implements Entity {

    protected GraphTraversal<Vertex, Vertex> traversal;

    /**
     * Initialised with a new Vertex.
     *
     * @param traversalSource a source for the new vertex
     */
    public GremlinEntity(GraphTraversalSource traversalSource) {
        traversal = traversalSource.addV("Entity");
    }

    /**
     * Initialised with an existing Vertex with the specified identifier.
     *
     * @param traversalSource a source of the existing vertex
     * @param id a vertex identifier
     */
    public GremlinEntity(GraphTraversalSource traversalSource, String id) {
        traversal = traversalSource.V(id);
    }

    @Override @SuppressWarnings("unchecked")
    public GremlinEntity name(String name) {
        traversal = traversal.property("name", name);
        return this;
    }

    @Override
    public boolean isSupported(Property property) {
        return EnumSet.of(Uid, Name).contains(property);
    }

    @Override
    public <V> V getProperty(Property property) {
        switch (property) {
            case Name:
                return (V) traversal.next().property("name").value();
            case Uid:
                return (V) traversal.next().id();
        }
        throw new IllegalArgumentException(String.format("Unsupported property: %s", property));
    }
}
