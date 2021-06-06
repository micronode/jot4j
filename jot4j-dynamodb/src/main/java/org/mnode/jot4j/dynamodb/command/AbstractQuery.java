package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

/**
 * Base class for queries to return entities without modification of persisted data (e.g. list, get operations).
 *
 * @param <T>
 */
public abstract class AbstractQuery<T> {

    protected final AmazonDynamoDB dynamoDB;

    protected final DynamoDBMapperConfig mapperConfig;

    public AbstractQuery(AmazonDynamoDB dynamoDB) {
        this(dynamoDB, DynamoDBMapperConfig.DEFAULT);
    }

    public AbstractQuery(AmazonDynamoDB dynamoDB, DynamoDBMapperConfig mapperConfig) {
        this.dynamoDB = dynamoDB;
        this.mapperConfig = mapperConfig;
    }

     public abstract <R extends T> PaginatedQueryList<R> execute(DynamoDBQueryExpression<R> query, Class<R> typeClass);
}
