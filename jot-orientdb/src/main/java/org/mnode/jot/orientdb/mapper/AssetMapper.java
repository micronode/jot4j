package org.mnode.jot.orientdb.mapper;

import com.orientechnologies.orient.core.record.OVertex;
import org.mnode.jot.schema.Asset;
import org.mnode.jot.schema.command.PropertyName;

import static org.mnode.jot.schema.PropertyAccessor.Property.AssetType;

public class AssetMapper extends AbstractEntityMapper<Asset> {

    @Override
    public void map(Asset from, OVertex to) {
        super.map(from, to);
        to.setProperty(PropertyName.AssetType.getValue(), from.getProperty(AssetType));
    }
}
