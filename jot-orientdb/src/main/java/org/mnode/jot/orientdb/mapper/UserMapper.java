package org.mnode.jot.orientdb.mapper;

import com.orientechnologies.orient.core.record.OVertex;
import org.mnode.jot.schema.User;

public class UserMapper extends AbstractEntityMapper<User> {

    @Override
    public void map(User from, OVertex to) {
        super.map(from, to);
//        to.setProperty(Note.toString(), from.getNote());
    }
}
