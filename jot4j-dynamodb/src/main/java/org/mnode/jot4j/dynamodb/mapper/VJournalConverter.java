package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.fortuna.ical4j.model.component.VJournal;
import org.mnode.ical4j.json.jot.JotJournalMapper;
import org.mnode.ical4j.json.jot.JotJournalSerializer;

import java.io.IOException;
import java.util.Map;

public class VJournalConverter implements DynamoDBTypeConverter<Map<String, String>, VJournal> {

    private final ObjectMapper mapper;

    public VJournalConverter() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(VJournal.class, new JotJournalMapper(VJournal.class));
        module.addSerializer(VJournal.class, new JotJournalSerializer(VJournal.class));
        mapper = new ObjectMapper();
        mapper.registerModule(module);
    }

    @Override
    public Map<String, String> convert(VJournal object) {
        try {
            return mapper.readValue(mapper.writeValueAsString(object), Map.class);
//            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VJournal unconvert(Map<String, String> object) {
        try {
            return mapper.readValue(mapper.writeValueAsString(object), VJournal.class);
//            return mapper.readValue(object, VJournal.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
