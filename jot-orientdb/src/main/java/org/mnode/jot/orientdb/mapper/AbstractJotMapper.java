package org.mnode.jot.orientdb.mapper;

import com.orientechnologies.orient.core.record.OVertex;
import org.mnode.jot.schema.Jot;
import org.mnode.jot.schema.PropertyAccessor;
import org.mnode.jot.schema.command.PropertyMapper;
import org.mnode.jot.schema.command.PropertyName;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.mnode.jot.schema.PropertyAccessor.Property.*;
import static org.mnode.jot.schema.command.PropertyName.CreatedAt;
import static org.mnode.jot.schema.command.PropertyName.UID;

public abstract class AbstractJotMapper<T extends Jot> implements PropertyMapper<T, OVertex> {

    @Override
    public void map(T from, OVertex to) {
        to.setProperty(UID.getValue(), from.getProperty(Uid));
        to.setProperty(PropertyName.Summary.getValue(), from.getProperty(Summary));
        Instant createdAt = from.getProperty(PropertyAccessor.Property.CreatedAt);
        Optional.ofNullable(createdAt).ifPresent(instant -> to.setProperty(CreatedAt.getValue(),
                Date.from(instant)));

        Instant updatedAt = from.getProperty(UpdatedAt);
        to.setProperty(PropertyName.UpdatedAt.getValue(), Date.from(Optional.ofNullable(updatedAt).orElse(Instant.now())));
    }
}
