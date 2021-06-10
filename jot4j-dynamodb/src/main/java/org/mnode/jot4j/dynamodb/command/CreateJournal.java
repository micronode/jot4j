package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VJournal;
import net.fortuna.ical4j.model.property.Attendee;
import org.mnode.jot4j.dynamodb.mapper.Journal;
import org.mnode.jot4j.dynamodb.mapper.JournalRecurrence;

import java.util.ArrayList;
import java.util.List;

public class CreateJournal extends AbstractCommand<VJournal> implements CreateCommand {

    public CreateJournal(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    public void execute(VJournal input) {
        List<Object> model = new ArrayList<>();

        if (input.getProperty(Property.RECURRENCE_ID) != null) {
            JournalRecurrence journalRecurrence = createJournalRecurrence(input);
            model.add(journalRecurrence);
        } else {
            Journal journal = createJournal(input);
            model.add(journal);
        }

        input.getProperties(Property.ATTENDEE).forEach(attendee -> {
            model.add(createAttendee(input, "VJOURNAL", (Attendee) attendee));
        });

        mapper.batchSave(model.toArray());
    }

    @Override
    public void executeBatch(VJournal... input) {

    }
}
