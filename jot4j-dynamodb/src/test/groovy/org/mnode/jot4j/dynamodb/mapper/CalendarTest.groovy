package org.mnode.jot4j.dynamodb.mapper

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import net.fortuna.ical4j.model.Property
import org.mnode.jot4j.dynamodb.AbstractIntegrationTest

class CalendarTest extends AbstractIntegrationTest {

    def 'create calendar'() {
        when: 'a calendar is saved'
        Calendar cal = []
        cal.uid = calendar1.getProperty(Property.UID).value
        cal.data = calendar1
        mapper.save(cal)

        then: 'the item count matches expected'
        dynamoDB.describeTable("jotCal").getTable().getItemCount() == 1

        and: 'retrieved item matches expected'
        def list = mapper.query(Calendar, new DynamoDBQueryExpression<Card>()
                .withExpressionAttributeValues(Map.of(":pk", new AttributeValue().withS("CALENDAR#123")))
                .withKeyConditionExpression("PK = :pk"))

        list[0].uid == calendar1.getProperty(Property.UID).value
    }
}
