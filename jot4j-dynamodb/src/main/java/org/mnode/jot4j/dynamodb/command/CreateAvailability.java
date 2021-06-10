package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import net.fortuna.ical4j.model.component.VAvailability;
import org.mnode.jot4j.dynamodb.mapper.Availability;

import java.util.ArrayList;
import java.util.List;

public class CreateAvailability extends AbstractCommand<VAvailability> implements CreateCommand {

    public CreateAvailability(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    public void execute(VAvailability input) {
        List<Object> model = new ArrayList<>();

        Availability availability = createAvailability(input);
        model.add(availability);

        input.getAvailable().forEach(available -> {

        });

        mapper.batchSave(model.toArray());
    }

    @Override
    public void executeBatch(VAvailability... input) {

    }
}
