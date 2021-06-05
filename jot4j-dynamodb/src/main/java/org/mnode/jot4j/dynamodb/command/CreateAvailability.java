package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import net.fortuna.ical4j.model.component.VAvailability;

public class CreateAvailability extends AbstractCreateCommand<VAvailability> {

    public CreateAvailability(AmazonDynamoDB dynamoDB) {
        super(dynamoDB);
    }

    @Override
    public void execute(VAvailability input) {

    }

    @Override
    public void executeBatch(VAvailability... input) {

    }
}
