package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VAvailability;
import net.fortuna.ical4j.model.property.Organizer;
import org.mnode.jot4j.dynamodb.mapper.Availability;

import java.util.ArrayList;
import java.util.List;

public class CreateAvailability extends AbstractCommand<VAvailability> implements CreateCommand {

    public CreateAvailability(AmazonDynamoDB dynamoDB) {
        super(dynamoDB);
    }

    @Override
    public void execute(VAvailability input) {
        List<Object> model = new ArrayList<>();

        Availability availability = createAvailability(input);
        model.add(availability);

        input.getAvailable().forEach(available -> {

        });
        Organizer organizer = input.getProperty(Property.ORGANIZER);
        if (organizer != null) {
            model.add(createOrganizer(input, "VAVAILABILITY", input.getProperty(Property.ORGANIZER)));
        }

        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        mapper.batchSave(model.toArray());
    }

    @Override
    public void executeBatch(VAvailability... input) {

    }
}
