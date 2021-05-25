package org.mnode.jot.json;

import com.github.jsonj.JsonArray;
import com.github.jsonj.JsonObject;
import org.mnode.jot.schema.Jot;
import org.mnode.jot.schema.Label;
import org.mnode.jot.schema.Person;

import java.util.Arrays;
import java.util.EnumSet;

import static org.mnode.jot.schema.PropertyAccessor.Property.Summary;
import static org.mnode.jot.schema.PropertyAccessor.Property.Uid;

public class JsonJot implements Jot {

    protected JsonObject jsonObject;

    public JsonJot(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public JsonJot summary(String summary) {
        jsonObject.put("summary", summary);
        return this;
    }

    @Override
    public JsonJot authoredBy(Person... authors) {
        jsonObject.put("authors", new JsonArray(Arrays.asList(authors)));
        return this;
    }

    @Override
    public JsonJot taggedWith(Label... labels) {
        jsonObject.put("labels", new JsonArray(Arrays.asList(labels)));
        return this;
    }

    @Override
    public boolean isSupported(Property property) {
        return EnumSet.of(Uid, Summary).contains(property);
    }

    @Override
    public <V> V getProperty(Property property) {
        return null;
    }
}
