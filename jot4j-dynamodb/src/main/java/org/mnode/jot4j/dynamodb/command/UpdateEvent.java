package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.RecurrenceId;
import net.fortuna.ical4j.model.property.Uid;
import org.mnode.jot4j.dynamodb.mapper.Event;
import org.mnode.jot4j.dynamodb.mapper.EventRecurrence;

import java.util.List;

/**
 * Update an existing event or event recurrence.
 *
 * NOTE: To enable event replacement specify the CLOBBER {@link DynamoDBMapperConfig.SaveBehavior} in the mapper config.
 */
public class UpdateEvent extends AbstractCommand<VEvent> implements GetQuery {

    public UpdateEvent(AmazonDynamoDB dynamoDB) {
        super(dynamoDB);
    }

    public UpdateEvent(AmazonDynamoDB dynamoDB, DynamoDBMapperConfig mapperConfig) {
        super(dynamoDB, mapperConfig);
    }

    @Override
    public void execute(VEvent input) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB, mapperConfig);

        Uid uid = input.getProperty(Property.UID);
        RecurrenceId recurrenceId = input.getProperty(Property.RECURRENCE_ID);
        List<? extends Event> result;
        if (recurrenceId != null) {
            result = mapper.query(EventRecurrence.class, getRecurrence(uid.getValue(),
                    recurrenceId.getValue(), "EVENT"));
        } else {
            result = mapper.query(Event.class, getComponent(uid.getValue(), "EVENT"));
        }

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Event doesn't exist");
        }
        result.get(0).setData(input);

        mapper.save(result.get(0));
    }

    @Override
    public void executeBatch(VEvent... input) {
        if (input.length == 0) {
            return;
        }
    }
}
