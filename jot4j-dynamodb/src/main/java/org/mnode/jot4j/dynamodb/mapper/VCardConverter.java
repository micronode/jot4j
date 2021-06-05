package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.fortuna.ical4j.vcard.VCard;

import java.io.IOException;
import java.util.Map;

public class VCardConverter implements DynamoDBTypeConverter<Map<String, String>, VCard> {

    private final ObjectMapper mapper;

    public VCardConverter() {
        SimpleModule module = new SimpleModule();
//        module.addDeserializer(VCard.class, new JotCardMapper());
//        module.addSerializer(VCard.class, new JotCardSerializer());
        mapper = new ObjectMapper();
        mapper.registerModule(module);
    }

    @Override
    public Map<String, String> convert(VCard object) {
        try {
            return mapper.readValue(mapper.writeValueAsString(object), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VCard unconvert(Map<String, String> object) {
        try {
            return mapper.readValue(mapper.writeValueAsString(object), VCard.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
