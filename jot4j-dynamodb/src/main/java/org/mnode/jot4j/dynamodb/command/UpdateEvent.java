package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.RecurrenceId;
import net.fortuna.ical4j.model.property.Uid;
import org.mnode.jot4j.dynamodb.mapper.Event;
import org.mnode.jot4j.dynamodb.mapper.EventRecurrence;

import java.util.*;

/**
 * Update an existing event or event recurrence.
 *
 * NOTE: To enable event replacement specify the CLOBBER {@link DynamoDBMapperConfig.SaveBehavior} in the mapper config.
 */
public class UpdateEvent extends AbstractCreateCommand<VEvent> {

    public UpdateEvent(AmazonDynamoDB dynamoDB) {
        super(dynamoDB);
    }

    public UpdateEvent(AmazonDynamoDB dynamoDB, DynamoDBMapperConfig mapperConfig) {
        super(dynamoDB, mapperConfig);
    }

    @Override
    public void execute(VEvent input) {
        List<Object> model = new ArrayList<>();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB, mapperConfig);

        Uid uid = input.getProperty(Property.UID);
        RecurrenceId recurrenceId = input.getProperty(Property.RECURRENCE_ID);
        List<? extends Event> result;
        if (recurrenceId != null) {
            Map<String, AttributeValue> attributes = new HashMap<>();
            attributes.put(":val1", new AttributeValue().withS("VEVENT#" + uid.getValue()));
            attributes.put(":val2", new AttributeValue().withS("RECURRENCE#" + recurrenceId.getValue()));

            result = mapper.query(EventRecurrence.class, new DynamoDBQueryExpression<EventRecurrence>()
                    .withKeyConditionExpression("PK = :val1")
                    .withKeyConditionExpression("begins_with(SK, :val2)")
                    .withExpressionAttributeValues(attributes)
                    .withFilterExpression("TYPE = EVENT_RECURRENCE")
                    .withScanIndexForward(false)
                    .withLimit(1));
        } else {
            result = mapper.query(Event.class, new DynamoDBQueryExpression<Event>()
                    .withKeyConditionExpression("PK = :val1")
                    .withExpressionAttributeValues(
                            Collections.singletonMap(":val1", new AttributeValue().withS("VEVENT#" + uid.getValue())))
                    .withFilterExpression("TYPE = EVENT")
                    .withScanIndexForward(false)
                    .withLimit(1));
        }


        if (result.isEmpty()) {
            throw new IllegalArgumentException("Event doesn't exist");
        }
        result.get(0).setData(input);
        model.add(result.get(0));

        mapper.batchSave(model.toArray());
    }

    @Override
    public void executeBatch(VEvent... input) {
        if (input.length == 0) {
            return;
        }
    }
}
