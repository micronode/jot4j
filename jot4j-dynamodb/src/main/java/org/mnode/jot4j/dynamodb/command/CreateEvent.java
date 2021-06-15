package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Attendee;
import org.mnode.jot4j.dynamodb.mapper.Event;
import org.mnode.jot4j.dynamodb.mapper.EventRecurrence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Creates a new event or event recurrence item and any encapsulated alarms.
 *
 * NOTE: When used in conjunction with jotapi it is expected the event data will not contain any alarms as they
 * are separated by distinct API endpoints.
 */
public class CreateEvent extends AbstractCommand<VEvent> implements CreateCommand {

    public CreateEvent(DynamoDBMapper mapper, String ownerId, String groupId) {
        super(mapper, ownerId, groupId);
    }

    @Override
    public void execute(VEvent input) {
        List<Object> model = new ArrayList<>();

        if (input.getProperty(Property.RECURRENCE_ID) != null) {
            EventRecurrence eventRecurrence = createEventRecurrence(input);
            model.add(eventRecurrence);
        } else {
            Event event = createEvent(input, ownerId, groupId);
            model.add(event);
        }

        input.getAlarms().forEach(alarm -> {
            model.add(createAlarm(alarm, input));
            model.addAll(alarm.getProperties(Property.ATTENDEE).stream().map(attendee ->
                    createAttendee(alarm, "VALARM", (Attendee) attendee)).collect(Collectors.toList()));
        });
        input.getProperties(Property.ATTENDEE).forEach(attendee -> {
            model.add(createAttendee(input, "VEVENT", (Attendee) attendee));
        });

        mapper.batchSave(model.toArray());
    }

    @Override
    public void executeBatch(VEvent... input) {
        if (input.length == 0) {
            return;
        }
        List<Object> model = new ArrayList<>();

        Arrays.stream(input).forEach(event -> {
            if (event.getProperty(Property.RECURRENCE_ID) != null) {
                EventRecurrence eventRecurrence = createEventRecurrence(event);
                model.add(eventRecurrence);
            } else {
                Event event1 = createEvent(event, ownerId, groupId);
                model.add(event1);
            }

            event.getAlarms().forEach(alarm -> {
                model.add(createAlarm(alarm, event));
                model.add(alarm.getProperties(Property.ATTENDEE).stream().map(attendee ->
                        createAttendee(alarm, "VALARM", (Attendee) attendee)));
            });
            event.getProperties(Property.ATTENDEE).forEach(attendee -> {
                model.add(createAttendee(event, "VEVENT", (Attendee) attendee));
            });
        });

        mapper.batchSave(model.toArray());
    }
}
