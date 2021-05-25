package org.mnode.jot.schema;

public interface PropertyAccessor {

    enum Property {
        Uid, Name,

        Color, Url,

        Summary, Note,

        Author,

        CreatedAt, UpdatedAt,

        AssetType, TaskType
    }

    boolean isSupported(Property property);

    <V> V getProperty(Property property);
}
