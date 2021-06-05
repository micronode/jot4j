package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.fortuna.ical4j.model.component.VAlarm;
import org.mnode.ical4j.json.jot.JotAlarmMapper;
import org.mnode.ical4j.json.jot.JotAlarmSerializer;

import java.io.IOException;
import java.util.Map;

public class VAlarmConverter implements DynamoDBTypeConverter<Map<String, String>, VAlarm> {

    private final ObjectMapper mapper;

    public VAlarmConverter() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(VAlarm.class, new JotAlarmMapper(VAlarm.class));
        module.addSerializer(VAlarm.class, new JotAlarmSerializer(VAlarm.class));
        mapper = new ObjectMapper();
        mapper.registerModule(module);
    }

    @Override
    public Map<String, String> convert(VAlarm object) {
        try {
            return mapper.readValue(mapper.writeValueAsString(object), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VAlarm unconvert(Map<String, String> object) {
        try {
            return mapper.readValue(mapper.writeValueAsString(object), VAlarm.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
