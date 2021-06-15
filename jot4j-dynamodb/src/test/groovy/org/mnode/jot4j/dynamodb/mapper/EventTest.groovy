package org.mnode.jot4j.dynamodb.mapper


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import net.fortuna.ical4j.model.ContentBuilder
import org.mnode.jot4j.dynamodb.AbstractIntegrationTest
import org.mnode.jot4j.dynamodb.query.KeyCriteria
import org.mnode.jot4j.dynamodb.query.QuerySupport

class EventTest extends AbstractIntegrationTest implements QuerySupport {

    def 'create event'() {
        when: 'an event is saved'
        Event event = []
        event.groupId = '1000'
        event.uid = '1234'
        event.data = new ContentBuilder().vevent() {
            uid '1234'
        }
        mapper.save(event)
        
        then: 'the item count matches expected'
        dynamoDB.describeTable(tableName).getTable().getItemCount() == 1

        and: 'retrieved item matches expected'
        def list = mapper.query(Event, new DynamoDBQueryExpression<Event>()
                .withExpressionAttributeValues(
                        Map.of(":val1", new AttributeValue().withS("GROUP#1000#EVENT#1234")))
                .withKeyConditionExpression("PK = :val1"))

        list[0].sequence == event.sequence
    }

    def 'create event with auto-generated uid'() {
        when: 'an event is saved'
        Event event = []
        event.groupId = '1000'
        event.data = new ContentBuilder().vevent() {
            summary 'Test event without UID'
        }
        mapper.save(event)

        then: 'the item count matches expected'
        assertTableItemCount(1)

        and: 'retrieved item matches expected'
        def criteria = new KeyCriteria().withTypeKey('EVENT').withIndexName('GSI1')
        def list = mapper.query(Event, filterTypes(criteria))

        list[0].sequence == event.sequence
    }
}
