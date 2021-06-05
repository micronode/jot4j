package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.fortuna.ical4j.model.Calendar;
import org.mnode.ical4j.json.jot.JotCalendarMapper;
import org.mnode.ical4j.json.jot.JotCalendarSerializer;

import java.io.IOException;
import java.util.Map;

public class CalendarConverter implements DynamoDBTypeConverter<Map<String, String>, Calendar> {

    private final ObjectMapper mapper;

    public CalendarConverter() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Calendar.class, new JotCalendarMapper(Calendar.class));
        module.addSerializer(Calendar.class, new JotCalendarSerializer(Calendar.class));
        mapper = new ObjectMapper();
        mapper.registerModule(module);
    }

    @Override
    public Map<String, String> convert(Calendar object) {
        try {
            return mapper.readValue(mapper.writeValueAsString(object), Map.class);
//            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Calendar unconvert(Map<String, String> object) {
        try {
            return mapper.readValue(mapper.writeValueAsString(object), Calendar.class);
//            return mapper.readValue(object, Calendar.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
