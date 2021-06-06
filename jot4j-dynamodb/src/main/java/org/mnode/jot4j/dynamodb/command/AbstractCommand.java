package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

/**
 * Base class for commands that may modify persisted data (i.e. create, update, delete operations).
 *
 * @param <T>
 */
public abstract class AbstractCommand<T> {

    protected final AmazonDynamoDB dynamoDB;

    protected final DynamoDBMapperConfig mapperConfig;

    public AbstractCommand(AmazonDynamoDB dynamoDB) {
        this(dynamoDB, DynamoDBMapperConfig.DEFAULT);
    }

    public AbstractCommand(AmazonDynamoDB dynamoDB, DynamoDBMapperConfig mapperConfig) {
        this.dynamoDB = dynamoDB;
        this.mapperConfig = mapperConfig;
    }

    public abstract void execute(T input);

    public abstract void executeBatch(T... input);
}
