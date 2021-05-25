package org.mnode.jot.orientdb.command;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.metadata.schema.OType;
import org.mnode.jot.orientdb.parser.SchemaClass;
import org.mnode.jot.orientdb.parser.SchemaLinkProperty;
import org.mnode.jot.orientdb.parser.SchemaProperty;
import org.mnode.jot.schema.command.AddCommand;

import java.util.Arrays;
import java.util.stream.Collectors;

public class OrientDBAddSchemaClass extends AbstractOrientDBCommand implements AddCommand<SchemaClass, OClass> {

    public OrientDBAddSchemaClass(ODatabaseDocument session) {
        super(session);
    }

    private OProperty getProperty(SchemaProperty property, OType propertyType, OClass clazz) {
        OProperty prop = clazz.getProperty(property.getName());
        if (prop != null && !prop.getType().equals(propertyType)) {
            clazz.dropProperty(property.getName());
            return null;
        } else {
            return prop;
        }
    }

    @Override
    public OClass add(SchemaClass schema) {
        OClass clazz = getSession().createClassIfNotExist(schema.getName(), schema.getSuperclasses());
        if (!clazz.getSuperClasses().stream().map(oClass -> oClass.getName()).collect(Collectors.toList())
                .equals(Arrays.asList(schema.getSuperclasses()))) {
            clazz.setSuperClasses(Arrays.stream(schema.getSuperclasses())
                    .map(s -> getSession().getClass(s)).collect(Collectors.toList()));
        }
        clazz.setAbstract(schema.isAbstract());

        for (SchemaProperty property : schema.getProperties()) {
            OType propertyType = OType.valueOf(property.getType());
            OProperty prop = getProperty(property, propertyType, clazz);
            if (prop == null) {
                prop = clazz.createProperty(property.getName(), propertyType);
            }
            prop.setMandatory(property.isMandatory());
        }

        for (SchemaLinkProperty property : schema.getLinkProperties()) {
            OType propertyType = OType.valueOf(property.getType());
            OClass linkedClass = getSession().getClass(property.getSchemaClass());
            OProperty prop = getProperty(property, propertyType, clazz);
            if (prop == null) {
                prop = clazz.createProperty(property.getName(), propertyType, linkedClass);
            }
//            prop.setLinkedClass(linkedClass);
            prop.setMandatory(property.isMandatory());
        }
        return clazz;
    }
}
