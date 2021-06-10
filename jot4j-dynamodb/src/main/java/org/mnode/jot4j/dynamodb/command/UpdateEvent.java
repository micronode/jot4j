package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Categories;
import net.fortuna.ical4j.model.property.RecurrenceId;
import net.fortuna.ical4j.model.property.Uid;
import org.mnode.jot4j.dynamodb.mapper.Event;
import org.mnode.jot4j.dynamodb.mapper.EventRecurrence;
import org.mnode.jot4j.dynamodb.query.MatchQuery;

import java.util.List;

/**
 * Update an existing event or event recurrence.
 *
 * NOTE: To enable event replacement specify the PUT {@link DynamoDBMapperConfig.SaveBehavior} in the mapper config.
 */
public class UpdateEvent extends AbstractCommand<VEvent> implements MatchQuery {

    public UpdateEvent(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    public void execute(VEvent input) {
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
        Categories categories = input.getProperty(Property.CATEGORIES);
        if (categories != null) {
//            result.get(0).setCategories(new HashSet<String>(categories.getCategories()));
        }

        mapper.save(result.get(0));
    }

    @Override
    public void executeBatch(VEvent... input) {
        if (input.length == 0) {
            return;
        }
    }
}
