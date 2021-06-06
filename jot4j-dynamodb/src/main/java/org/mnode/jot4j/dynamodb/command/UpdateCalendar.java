package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Property;

import java.util.ArrayList;
import java.util.List;

public class UpdateCalendar extends AbstractCommand<Calendar> {

    public UpdateCalendar(AmazonDynamoDB dynamoDB) {
        super(dynamoDB);
    }

    @Override
    public void execute(Calendar input) {
        List<Object> model = new ArrayList<>();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

        org.mnode.jot4j.dynamodb.mapper.Calendar calendar = mapper.load(org.mnode.jot4j.dynamodb.mapper.Calendar.class,
                "CALENDAR#" + input.getProperty(Property.UID).getValue());
        calendar.setData(input);
        model.add(calendar);

        mapper.batchSave(model.toArray());
    }

    @Override
    public void executeBatch(Calendar... input) {
        if (input.length == 0) {
            return;
        }
    }
}
