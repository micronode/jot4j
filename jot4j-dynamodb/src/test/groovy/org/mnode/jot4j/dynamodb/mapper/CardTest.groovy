package org.mnode.jot4j.dynamodb.mapper

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import org.mnode.jot4j.dynamodb.AbstractIntegrationTest

class CardTest extends AbstractIntegrationTest {

    def 'create card'() {
        given: 'a dynamodb mapper'
        DynamoDBMapper mapper = [dynamoDB]
//        DynamoDBMapperConfig config = DynamoDBMapperConfig.builder()
//                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
//                .build()

        when: 'a card is saved'
        Card card = []
        card.uid = '1234'
        card.fn = ['Mr', 'Jones']
        mapper.save(card)
        
        then: 'the item count matches expected'
        dynamoDB.describeTable("jotCard").getTable().getItemCount() == 1

        and: 'retrieved item matches expected'
        def list = mapper.query(Card, new DynamoDBQueryExpression<Card>()
        // reads from GSI can't be consistent
//                .withConsistentRead(false)
                .withExpressionAttributeValues(
                        Map.of(":val1", new AttributeValue().withS("CARD#1234")))
//                .withIndexName("GSI1")
                .withKeyConditionExpression("PK = :val1"))

//        Card retrievedCard = mapper.load(Card, 'CARD#1234', 'REV#1')
        list[0].revision == card.revision
    }
}
