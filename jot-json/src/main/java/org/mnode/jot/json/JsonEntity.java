package org.mnode.jot.json;

import com.github.jsonj.JsonObject;
import org.mnode.jot.schema.Entity;

import java.io.IOException;
import java.io.Writer;
import java.util.EnumSet;

import static org.mnode.jot.schema.PropertyAccessor.Property.Name;
import static org.mnode.jot.schema.PropertyAccessor.Property.Uid;

public class JsonEntity implements Entity {

    protected JsonObject jsonObject;

    public JsonEntity(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override @SuppressWarnings("unchecked")
    public JsonEntity name(String name) {
        jsonObject.put("name", name);
        return this;
    }

    @Override
    public boolean isSupported(Property property) {
        return EnumSet.of(Uid, Name).contains(property);
    }

    @Override @SuppressWarnings("unchecked")
    public <V> V getProperty(Property property) {
        switch (property) {
            case Name:
                return (V) jsonObject.get("name");
            case Uid:
                return (V) jsonObject.get("id");
        }
        throw new IllegalArgumentException(String.format("Unsupported property: %s", property));
    }

    public void writeTo(Writer writer) throws IOException {
        writer.write(jsonObject.toString());
    }
}
