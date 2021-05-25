package org.mnode.jot.orientdb.mapper;

import com.orientechnologies.orient.core.record.OVertex;
import org.mnode.jot.schema.Entity;
import org.mnode.jot.schema.command.PropertyMapper;
import org.mnode.jot.schema.command.PropertyName;

import static org.mnode.jot.schema.PropertyAccessor.Property.Name;

public abstract class AbstractEntityMapper<T extends Entity> implements PropertyMapper<T, OVertex> {

    @Override
    public void map(T from, OVertex to) {
        to.setProperty(PropertyName.Name.getValue(), from.getProperty(Name));
    }
}
