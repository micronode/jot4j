package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.fortuna.ical4j.model.component.Available;
import org.mnode.ical4j.json.jot.JotAvailableMapper;
import org.mnode.ical4j.json.jot.JotAvailableSerializer;

import java.io.IOException;
import java.util.Map;

public class AvailableConverter implements DynamoDBTypeConverter<Map<String, String>, Available> {

    private final ObjectMapper mapper;

    public AvailableConverter() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Available.class, new JotAvailableMapper(Available.class));
        module.addSerializer(Available.class, new JotAvailableSerializer(Available.class));
        mapper = new ObjectMapper();
        mapper.registerModule(module);
    }

    @Override
    public Map<String, String> convert(Available object) {
        try {
            return mapper.readValue(mapper.writeValueAsString(object), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Available unconvert(Map<String, String> object) {
        try {
            return mapper.readValue(mapper.writeValueAsString(object), Available.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
