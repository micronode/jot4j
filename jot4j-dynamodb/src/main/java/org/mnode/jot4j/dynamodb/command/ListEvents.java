package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.mnode.jot4j.dynamodb.mapper.Event;

import java.util.Collections;

/**
 * Return events matching specified criteria.
 *
 * Criteria should include:
 *  * Calendar UID
 *  * Categories
 *  * Organizers
 *  * Attendees
 */
public class ListEvents extends AbstractQuery<Event> {

    public ListEvents(AmazonDynamoDB dynamoDB) {
        super(dynamoDB);
    }

    @Override
    public <R extends Event> PaginatedQueryList<R> execute(DynamoDBQueryExpression<R> query, Class<R> typeClass) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        return mapper.query(typeClass, query);
    }

    public PaginatedQueryList<Event> execute(String uid) {
        return execute(new DynamoDBQueryExpression<Event>()
                .withExpressionAttributeValues(
                        Collections.singletonMap(":val1", new AttributeValue().withS("EVENT#" + uid)))
                .withKeyConditionExpression("PK = :val1"), Event.class);
    }

    public PaginatedQueryList<Event> execute(String... categories) {
        return execute(new DynamoDBQueryExpression<Event>()
                .withExpressionAttributeValues(
                        Collections.singletonMap(":val1", new AttributeValue().withS("EVENT#" + categories)))
                .withKeyConditionExpression("PK = :val1"), Event.class);
    }
}
