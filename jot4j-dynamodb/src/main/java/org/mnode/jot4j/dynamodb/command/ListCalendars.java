package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import org.mnode.jot4j.dynamodb.mapper.Calendar;

public class ListCalendars extends AbstractQuery<Calendar> {

    public ListCalendars(AmazonDynamoDB dynamoDB) {
        super(dynamoDB);
    }

    @Override
    public <R extends Calendar> PaginatedQueryList<R> execute(DynamoDBQueryExpression<R> query, Class<R> typeClass) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        return mapper.query(typeClass, query);
    }
}
