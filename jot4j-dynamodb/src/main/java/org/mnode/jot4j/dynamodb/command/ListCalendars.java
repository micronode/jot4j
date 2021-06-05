package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import org.mnode.jot4j.dynamodb.mapper.Calendar;

public class ListCalendars extends AbstractListCommand<Calendar> {

    public ListCalendars(AmazonDynamoDB dynamoDB) {
        super(dynamoDB);
    }

    public PaginatedQueryList<Calendar> execute(DynamoDBQueryExpression<Calendar> query) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        return mapper.query(Calendar.class, query);
    }
}
