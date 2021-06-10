package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.RecurrenceId;
import net.fortuna.ical4j.model.property.Uid;
import org.mnode.jot4j.dynamodb.mapper.Alarm;
import org.mnode.jot4j.dynamodb.mapper.Attendee;
import org.mnode.jot4j.dynamodb.mapper.Event;
import org.mnode.jot4j.dynamodb.mapper.EventRecurrence;
import org.mnode.jot4j.dynamodb.query.FilterQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Update an existing event or event recurrence.
 *
 * NOTE: To enable event replacement specify the CLOBBER {@link DynamoDBMapperConfig.SaveBehavior} in the mapper config.
 */
public class DeleteEvent extends AbstractCommand<VEvent> implements FilterQuery {

    public DeleteEvent(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    public void execute(VEvent input) {
        List<Object> model = new ArrayList<>();

        Uid uid = input.getProperty(Property.UID);
        RecurrenceId recurrenceId = input.getProperty(Property.RECURRENCE_ID);
        List<? extends Event> result;
        if (recurrenceId != null) {
            result = mapper.query(EventRecurrence.class, filterRecurrenceRevisions(uid.getValue(),
                    recurrenceId.getValue(), "EVENT"));
        } else {
            result = mapper.query(Event.class, filterComponentRevisions(uid.getValue(), "EVENT"));
        }

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Event doesn't exist");
        }
        model.addAll(result);

        // delete all references to the event (except when just deleting an event recurrence)..
        if (recurrenceId == null) {
            model.addAll(mapper.query(EventRecurrence.class,
                    filterComponentRecurrences(uid.getValue(), "EVENT")));
            model.addAll(mapper.query(Alarm.class,
                    filterComponentAlarms(uid.getValue(), "EVENT")));
            model.addAll(mapper.query(Attendee.class,
                    filterComponentAttendees(uid.getValue(), "EVENT")));
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
