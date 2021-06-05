package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.fortuna.ical4j.model.component.VEvent;
import org.mnode.ical4j.json.jot.JotEventMapper;
import org.mnode.ical4j.json.jot.JotEventSerializer;

import java.io.IOException;
import java.util.Map;

public class VEventConverter implements DynamoDBTypeConverter<Map<String, String>, VEvent> {

    private final ObjectMapper mapper;

    public VEventConverter() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(VEvent.class, new JotEventMapper(VEvent.class));
        module.addSerializer(VEvent.class, new JotEventSerializer(VEvent.class));
        mapper = new ObjectMapper();
        mapper.registerModule(module);
    }

    @Override
    public Map<String, String> convert(VEvent object) {
        try {
            return mapper.readValue(mapper.writeValueAsString(object), Map.class);
//            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VEvent unconvert(Map<String, String> object) {
        try {
            return mapper.readValue(mapper.writeValueAsString(object), VEvent.class);
//            return mapper.readValue(object, VEvent.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
