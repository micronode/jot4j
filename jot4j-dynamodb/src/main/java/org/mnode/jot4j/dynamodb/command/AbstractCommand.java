package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

/**
 * Base class for commands that may modify persisted data (i.e. create, update, delete operations).
 *
 * @param <T>
 */
public abstract class AbstractCommand<T> {

    protected final DynamoDBMapper mapper;

    public AbstractCommand(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public abstract void execute(T input);

    public abstract void executeBatch(T... input);
}
