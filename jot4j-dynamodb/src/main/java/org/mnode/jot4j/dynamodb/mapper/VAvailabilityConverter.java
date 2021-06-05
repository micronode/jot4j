package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.fortuna.ical4j.model.component.VAvailability;
import org.mnode.ical4j.json.jot.JotAvailabilityMapper;
import org.mnode.ical4j.json.jot.JotAvailabilitySerializer;

import java.io.IOException;
import java.util.Map;

public class VAvailabilityConverter implements DynamoDBTypeConverter<Map<String, String>, VAvailability> {

    private final ObjectMapper mapper;

    public VAvailabilityConverter() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(VAvailability.class, new JotAvailabilityMapper(VAvailability.class));
        module.addSerializer(VAvailability.class, new JotAvailabilitySerializer(VAvailability.class));
        mapper = new ObjectMapper();
        mapper.registerModule(module);
    }

    @Override
    public Map<String, String> convert(VAvailability object) {
        try {
            return mapper.readValue(mapper.writeValueAsString(object), Map.class);
//            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VAvailability unconvert(Map<String, String> object) {
        try {
            return mapper.readValue(mapper.writeValueAsString(object), VAvailability.class);
//            return mapper.readValue(object, VAvailability.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
