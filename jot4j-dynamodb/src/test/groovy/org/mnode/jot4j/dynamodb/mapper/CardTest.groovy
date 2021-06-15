package org.mnode.jot4j.dynamodb.mapper


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import org.mnode.jot4j.dynamodb.AbstractIntegrationTest

class CardTest extends AbstractIntegrationTest {

    def 'create card'() {
        when: 'a card is saved'
        Card card = []
        card.uid = '1234'
        card.fn = ['Mr', 'Jones']
        mapper.save(card)
        
        then: 'the item count matches expected'
        assertTableItemCount(1)

        and: 'retrieved item matches expected'
        def list = mapper.query(Card, new DynamoDBQueryExpression<Card>()
                .withExpressionAttributeValues(
                        Map.of(":val1", new AttributeValue().withS("CARD#1234")))
                .withKeyConditionExpression("PK = :val1"))

        list[0].revision == card.revision
    }
}
