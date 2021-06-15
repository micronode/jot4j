package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.fortuna.ical4j.vcard.VCard;
import org.mnode.ical4j.json.JCardMapper;
import org.mnode.ical4j.json.JCardSerializer;
import org.mnode.ical4j.json.jot.JotCardMapper;
import org.mnode.ical4j.json.jot.JotCardSerializer;

import java.io.IOException;
import java.util.Map;

/**
 * Convert {@link VCard} instances to/from JSON format(s).
 *
 * For "lossless" conversion to/from JSON use the JCal format.
 */
public class VCardConverter implements DynamoDBTypeConverter<Map<String, String>, VCard> {

    public enum Format {JOT, JCAL};

    private final ObjectMapper mapper;

    public VCardConverter() {
        this(Format.JOT);
    }

    public VCardConverter(Format format) {
        SimpleModule module = new SimpleModule();
        if (format == Format.JCAL) {
            module.addDeserializer(VCard.class, new JCardMapper(VCard.class));
            module.addSerializer(VCard.class, new JCardSerializer(VCard.class));
        } else {
            module.addDeserializer(VCard.class, new JotCardMapper(VCard.class));
            module.addSerializer(VCard.class, new JotCardSerializer(VCard.class));
        }
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
