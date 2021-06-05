package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Creates a new calendar item and any encapsulated components (events, alarms, todos, journals, availability, etc.).
 *
 * NOTE: When used in conjunction with jotapi it is expected the calendar data will not contain any components as they
 * are separated by distinct API endpoints.
 */
public class CreateCalendar extends AbstractCreateCommand<Calendar> {

    public CreateCalendar(AmazonDynamoDB dynamoDB) {
        super(dynamoDB);
    }

    @Override
    public void execute(Calendar input) {
        List<Object> model = new ArrayList<>();
        model.add(createCalendar(input));
        input.getComponents(Component.VEVENT).forEach(event -> {
                model.add(createEvent((VEvent) event));
                model.add(createCalendarEvent(input, (VEvent) event));
            }
        );

        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        mapper.batchSave(model.toArray());
    }

    @Override
    public void executeBatch(Calendar... input) {
        if (input.length == 0) {
            return;
        }

        List<Object> model = new ArrayList<>();
        Arrays.stream(input).forEach(calendar -> {
            model.add(createCalendar(calendar));
            calendar.getComponents(Component.VEVENT).forEach(event -> {
                    model.add(createEvent((VEvent) event));
                    model.add(createCalendarEvent(calendar, (VEvent) event));
                }
            );
        });

        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        mapper.batchSave(model.toArray());
    }
}
