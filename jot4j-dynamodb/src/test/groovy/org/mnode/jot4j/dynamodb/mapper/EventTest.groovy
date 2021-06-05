package org.mnode.jot4j.dynamodb.mapper

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import net.fortuna.ical4j.model.ContentBuilder
import org.mnode.jot4j.dynamodb.AbstractIntegrationTest

class EventTest extends AbstractIntegrationTest {

    def 'create event'() {
        given: 'a dynamodb mapper'
        DynamoDBMapper mapper = [dynamoDB]

        when: 'an event is saved'
        Event event = []
        event.uid = '1234'
        event.data = new ContentBuilder().vevent() {
            uid '1234'
        }
        mapper.save(event)
        
        then: 'the item count matches expected'
        dynamoDB.describeTable("jotCal").getTable().getItemCount() == 1

        and: 'retrieved item matches expected'
        def list = mapper.query(Event, new DynamoDBQueryExpression<Event>()
                .withExpressionAttributeValues(
                        Map.of(":val1", new AttributeValue().withS("EVENT#1234")))
                .withKeyConditionExpression("PK = :val1"))

        list[0].sequence == event.sequence
    }
}
