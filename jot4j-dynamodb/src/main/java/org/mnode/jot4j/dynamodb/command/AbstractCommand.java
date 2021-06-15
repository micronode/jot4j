package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

/**
 * Base class for commands that may modify persisted data (i.e. create, update, delete operations).
 *
 * @param <T>
 */
public abstract class AbstractCommand<T> {

    protected final DynamoDBMapper mapper;

    protected final String ownerId;

    protected final String groupId;

    public AbstractCommand(DynamoDBMapper mapper, String ownerId) {
        this(mapper, ownerId, null);
    }

    public AbstractCommand(DynamoDBMapper mapper, String ownerId, String groupId) {
        this.mapper = mapper;
        this.ownerId = ownerId;
        this.groupId = groupId;
    }

    public abstract void execute(T input);

    public abstract void executeBatch(T... input);
}
