package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.fortuna.ical4j.model.component.VToDo;
import org.mnode.ical4j.json.jot.JotToDoMapper;
import org.mnode.ical4j.json.jot.JotToDoSerializer;

import java.io.IOException;
import java.util.Map;

public class VToDoConverter implements DynamoDBTypeConverter<Map<String, String>, VToDo> {

    private final ObjectMapper mapper;

    public VToDoConverter() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(VToDo.class, new JotToDoMapper(VToDo.class));
        module.addSerializer(VToDo.class, new JotToDoSerializer(VToDo.class));
        mapper = new ObjectMapper();
        mapper.registerModule(module);
    }

    @Override
    public Map<String, String> convert(VToDo object) {
        try {
            return mapper.readValue(mapper.writeValueAsString(object), Map.class);
//            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VToDo unconvert(Map<String, String> object) {
        try {
            return mapper.readValue(mapper.writeValueAsString(object), VToDo.class);
//            return mapper.readValue(object, VToDo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
