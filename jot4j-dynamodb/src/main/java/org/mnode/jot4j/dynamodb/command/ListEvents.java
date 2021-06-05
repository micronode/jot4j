package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import org.mnode.jot4j.dynamodb.mapper.Event;

/**
 * Return events matching specified criteria.
 *
 * Criteria should include:
 *  * UID
 *  * Include recurrences (return latest version of event and all recurrence instances)
 *  * Include revisions (return all revisions of the event, and if "include recurrences = true" all revisions of recurrences)
 */
public class ListEvents extends AbstractListCommand<Event> {

    public ListEvents(AmazonDynamoDB dynamoDB) {
        super(dynamoDB);
    }

    public PaginatedQueryList<Event> execute(DynamoDBQueryExpression<Event> query) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        return mapper.query(Event.class, query);
    }
}
