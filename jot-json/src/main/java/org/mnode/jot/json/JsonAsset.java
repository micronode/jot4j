package org.mnode.jot.json;

import com.github.jsonj.JsonObject;
import org.mnode.jot.schema.Asset;
import org.mnode.jot.schema.AssetType;
import org.mnode.jot.schema.Entity;

public class JsonAsset extends JsonEntity implements Asset {

    public JsonAsset(JsonObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public JsonAsset assetType(AssetType assetType) {
        jsonObject.put("assetType", (String) assetType.getProperty(Property.Uid));
        return this;
    }

    @Override
    public JsonAsset owner(Entity owner) {
        jsonObject.put("owner", (String) owner.getProperty(Property.Uid));
        return this;
    }
}
