package org.mnode.jot.orientdb.parser;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SchemaParser {

    public List<SchemaClass> parse(InputStream in) {
        List<SchemaClass> classList = new ArrayList<>();

        Yaml yaml = new Yaml(new Constructor(SchemaClass.class));
        yaml.loadAll(in).forEach(o -> classList.add((SchemaClass) o));

        return classList;
    }
}
