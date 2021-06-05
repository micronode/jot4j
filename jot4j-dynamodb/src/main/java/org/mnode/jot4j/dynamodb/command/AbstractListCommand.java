package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

public abstract class AbstractListCommand<T> extends AbstractCommand {

    public AbstractListCommand(AmazonDynamoDB dynamoDB) {
        super(dynamoDB);
    }

    public abstract PaginatedQueryList<T> execute(DynamoDBQueryExpression<T> query);
}
