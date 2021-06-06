package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.RecurrenceId;
import net.fortuna.ical4j.model.property.Uid;
import org.mnode.jot4j.dynamodb.mapper.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Update an existing event or event recurrence.
 *
 * NOTE: To enable event replacement specify the CLOBBER {@link DynamoDBMapperConfig.SaveBehavior} in the mapper config.
 */
public class DeleteEvent extends AbstractCommand<VEvent> implements ListQuery {

    public DeleteEvent(AmazonDynamoDB dynamoDB) {
        super(dynamoDB);
    }

    public DeleteEvent(AmazonDynamoDB dynamoDB, DynamoDBMapperConfig mapperConfig) {
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
            result = mapper.query(EventRecurrence.class, listRecurrenceRevisions(uid.getValue(),
                    recurrenceId.getValue(), "EVENT"));
        } else {
            result = mapper.query(Event.class, listComponentRevisions(uid.getValue(), "EVENT"));
        }

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Event doesn't exist");
        }
        model.addAll(result);

        // delete all references to the event (except when just deleting an event recurrence)..
        if (recurrenceId == null) {
            model.addAll(mapper.query(EventRecurrence.class,
                    listComponentRecurrences(uid.getValue(), "EVENT")));
            model.addAll(mapper.query(CalendarEvent.class,
                    listComponentCalendars(uid.getValue(), "EVENT")));
            model.addAll(mapper.query(EventAlarm.class,
                    listComponentAlarms(uid.getValue(), "EVENT")));
            model.addAll(mapper.query(Organizer.class,
                    listComponentOrganizers(uid.getValue(), "EVENT")));
            model.addAll(mapper.query(Attendee.class,
                    listComponentAttendees(uid.getValue(), "EVENT")));
        }

        mapper.batchDelete(model.toArray());
    }

    @Override
    public void executeBatch(VEvent... input) {
        if (input.length == 0) {
            return;
        }
    }
}
