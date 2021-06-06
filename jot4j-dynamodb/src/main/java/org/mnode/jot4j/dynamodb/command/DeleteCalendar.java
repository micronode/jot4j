package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.property.Uid;
import org.mnode.jot4j.dynamodb.mapper.CalendarEvent;

import java.util.ArrayList;
import java.util.List;

public class DeleteCalendar extends AbstractCommand<Calendar> implements ListQuery {

    public DeleteCalendar(AmazonDynamoDB dynamoDB) {
        super(dynamoDB);
    }

    @Override
    public void execute(Calendar input) {
        List<Object> model = new ArrayList<>();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

        Uid uid = input.getProperty(Property.UID);
        org.mnode.jot4j.dynamodb.mapper.Calendar calendar = mapper.load(org.mnode.jot4j.dynamodb.mapper.Calendar.class,
                "CALENDAR#" + uid.getValue());
        model.add(calendar);

        List<CalendarEvent> events = mapper.query(CalendarEvent.class, listCalendarComponents(uid.getValue(), "EVENT"));
        model.addAll(events);

        mapper.batchDelete(model.toArray());
    }

    @Override
    public void executeBatch(Calendar... input) {
        if (input.length == 0) {
            return;
        }
    }
}
