package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VToDo;
import net.fortuna.ical4j.model.property.Attendee;
import org.mnode.jot4j.dynamodb.mapper.ToDo;
import org.mnode.jot4j.dynamodb.mapper.ToDoRecurrence;

import java.util.ArrayList;
import java.util.List;

public class CreateToDo extends AbstractCommand<VToDo> implements CreateCommand {

    public CreateToDo(DynamoDBMapper mapper, String ownerId, String groupId) {
        super(mapper, ownerId, groupId);
    }

    @Override
    public void execute(VToDo input) {
        List<Object> model = new ArrayList<>();

        if (input.getProperty(Property.RECURRENCE_ID) != null) {
            ToDoRecurrence toDoRecurrence = createToDoRecurrence(input);
            model.add(toDoRecurrence);
        } else {
            ToDo toDo = createToDo(input);
            model.add(toDo);
        }

        input.getAlarms().forEach(alarm -> {
            model.add(createAlarm(alarm, input));
            model.add(alarm.getProperties(Property.ATTENDEE).stream().map(attendee ->
                    createAttendee(alarm, "VALARM", (Attendee) attendee)));
        });
        input.getProperties(Property.ATTENDEE).forEach(attendee -> {
            model.add(createAttendee(input, "VTODO", (Attendee) attendee));
        });

        mapper.batchSave(model.toArray());
    }

    @Override
    public void executeBatch(VToDo... input) {

    }
}
