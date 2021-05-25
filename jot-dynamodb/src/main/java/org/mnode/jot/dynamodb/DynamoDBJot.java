package org.mnode.jot.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.mnode.jot.data.AbstractJot;
import org.mnode.jot.schema.Jot;
import org.mnode.jot.schema.Label;
import org.mnode.jot.schema.Person;

import java.util.function.Supplier;

public class DynamoDBJot extends AbstractJot<AmazonDynamoDB> {

    public DynamoDBJot(Supplier<AmazonDynamoDB> supplier) {
        super(supplier);
    }

    @Override
    public <T extends Jot> T summary(String summary) {
        return null;
    }

    @Override
    public <T extends Jot> T authoredBy(Person... authors) {
        return null;
    }

    @Override
    public <T extends Jot> T taggedWith(Label... labels) {
        return null;
    }

    @Override
    public boolean isSupported(Property property) {
        return false;
    }

    @Override
    public <V> V getProperty(Property property) {
        return null;
    }
}
