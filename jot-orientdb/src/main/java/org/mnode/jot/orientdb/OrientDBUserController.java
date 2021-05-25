package org.mnode.jot.orientdb;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import org.mnode.jot.orientdb.command.OrientDBAddUser;
import org.mnode.jot.schema.command.NodeController;
import org.mnode.jot.schema.model.User;

import java.util.List;

public class OrientDBUserController implements NodeController<User, String> {

    private final ODatabaseDocument session;

    public OrientDBUserController(ODatabaseDocument session) {
        this.session = session;
    }

    @Override
    public boolean add(User input) {
        return new OrientDBAddUser(session).add(input) != null;
    }

    @Override
    public List<User> get(String query) {
        return null;
    }

    @Override
    public List<User> remove(String query) {
        return null;
    }

    @Override
    public boolean update(User input) {
        return false;
    }
}
