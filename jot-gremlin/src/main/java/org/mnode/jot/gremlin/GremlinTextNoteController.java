package org.mnode.jot.gremlin;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.mnode.jot.schema.TextNote;
import org.mnode.jot.schema.command.NodeController;
import org.mnode.jot.schema.command.NodeType;

import java.util.List;

import static org.mnode.jot.schema.PropertyAccessor.Property.Note;

public class GremlinTextNoteController implements NodeController<TextNote, String> {

    private GraphTraversalSource traversalSource;

    @Override
    public boolean add(TextNote input) {
//        return TinkerGraph.open().traversal().V
//        AnonymousTraversalSource.traversal().withGraph(OrientGraph.getActiveGraph());
//        OrientGraph g = new OrientGraphFactory().getNoTx();
        return traversalSource.addV(NodeType.TextNote.toString()).property("note", input.getProperty(Note)).next() != null;
    }

    @Override
    public List<TextNote> get(String query) {
        return null;
    }

    @Override
    public List<TextNote> remove(String query) {
        return null;
    }

    @Override
    public boolean update(TextNote input) {
        return false;
    }
}
