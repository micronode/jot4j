package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.property.Uid;
import org.mnode.jot4j.dynamodb.mapper.Event;
import org.mnode.jot4j.dynamodb.query.FilterQuery;

import java.util.ArrayList;
import java.util.List;

public class DeleteCalendar extends AbstractCommand<Calendar> implements FilterQuery {

    public DeleteCalendar(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    public void execute(Calendar input) {
        List<Object> model = new ArrayList<>();

        Uid uid = input.getProperty(Property.UID);
        org.mnode.jot4j.dynamodb.mapper.Calendar calendar = mapper.load(org.mnode.jot4j.dynamodb.mapper.Calendar.class,
                "CALENDAR#" + uid.getValue());
        model.add(calendar);

        List<Event> events = mapper.query(Event.class, filterCalendarComponents(uid.getValue(), "EVENT"));
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
