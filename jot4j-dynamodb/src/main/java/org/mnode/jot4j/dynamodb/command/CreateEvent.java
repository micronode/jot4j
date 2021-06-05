package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.Organizer;
import org.mnode.jot4j.dynamodb.mapper.Event;
import org.mnode.jot4j.dynamodb.mapper.EventRecurrence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Creates a new event or event recurrence item and any encapsulated alarms.
 *
 * NOTE: When used in conjunction with jotapi it is expected the event data will not contain any alarms as they
 * are separated by distinct API endpoints.
 */
public class CreateEvent extends AbstractCreateCommand<VEvent> {

    public CreateEvent(AmazonDynamoDB dynamoDB) {
        super(dynamoDB);
    }

    @Override
    public void execute(VEvent input) {
        List<Object> model = new ArrayList<>();

        if (input.getProperty(Property.RECURRENCE_ID) != null) {
            EventRecurrence eventRecurrence = createEventRecurrence(input);
            model.add(eventRecurrence);
        } else {
            Event event = createEvent(input);
            model.add(event);
        }

        input.getAlarms().forEach(alarm -> {
            model.add(createAlarm(alarm));
            model.add(createOrganizer(alarm, "VALARM", alarm.getProperty(Property.ORGANIZER)));
            model.add(alarm.getProperties(Property.ATTENDEE).stream().map(attendee ->
                    createAttendee(alarm, "VALARM", (Attendee) attendee)));
            model.add(createEventAlarm(input, alarm));
        });
        Organizer organizer = input.getProperty(Property.ORGANIZER);
        if (organizer != null) {
            model.add(createOrganizer(input, "VEVENT", input.getProperty(Property.ORGANIZER)));
        }
        input.getProperties(Property.ATTENDEE).forEach(attendee -> {
            model.add(createAttendee(input, "VEVENT", (Attendee) attendee));
        });

        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
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
                Event event1 = createEvent(event);
                model.add(event1);
            }

            event.getAlarms().forEach(alarm -> {
                model.add(createAlarm(alarm));
                model.add(createOrganizer(alarm, "VALARM", alarm.getProperty(Property.ORGANIZER)));
                model.add(alarm.getProperties(Property.ATTENDEE).stream().map(attendee ->
                        createAttendee(alarm, "VALARM", (Attendee) attendee)));
                model.add(createEventAlarm(event, alarm));
            });
            model.add(createOrganizer(event, "VEVENT", event.getProperty(Property.ORGANIZER)));
            event.getProperties(Property.ATTENDEE).forEach(attendee -> {
                model.add(createAttendee(event, "VEVENT", (Attendee) attendee));
            });
        });

        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        mapper.batchSave(model.toArray());
    }
}
