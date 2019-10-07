package org.mnode.jot.schema;

public interface PropertyAccessor {

    enum Property {
        Uid, Name,

        Color,

        Summary, Note,

        Author,

        CreatedAt, UpdatedAt,

        AssetType
    }

    boolean isSupported(Property property);

    <V> V getProperty(Property property);
}
