package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;

public interface GetQuery {

    /**
     * Return the latest revision of the specified type with the specified UID.
     *
     * @param uid a component UID
     * @param type an item type (e.g. EVENT, JOURNAL, TODO, etc.)
     * @param <T> mapper type for results
     * @return a query expression
     */
    default <T> DynamoDBQueryExpression<T> getComponent(String uid, String type) {
        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put("pk", new AttributeValue().withS(type + "#" + uid));

        return new DynamoDBQueryExpression<T>()
                .withFilterExpression("TYPE = " + type)
                .withExpressionAttributeValues(attributes)
                .withKeyConditionExpression("PK = :pk")
                .withScanIndexForward(false)
                .withLimit(1);
    }

    /**
     * Return the latest revision of the specified type with the specified UID and recurrence ID.
     *
     * @param uid a component UID
     * @param recurrenceId a component recurrence ID
     * @param type an item type (e.g. EVENT, JOURNAL, TODO, etc.)
     * @param <T> mapper type for results
     * @return a query expression
     */
    default <T> DynamoDBQueryExpression<T> getRecurrence(String uid, String recurrenceId, String type) {
        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put("pk", new AttributeValue().withS(type + "#" + uid));
        attributes.put("sk", new AttributeValue().withS("RECURRENCE#" + recurrenceId));

        return new DynamoDBQueryExpression<T>()
                .withFilterExpression("TYPE = " + type + "_RECURRENCE")
                .withExpressionAttributeValues(attributes)
                .withKeyConditionExpression("PK = :pk")
                .withKeyConditionExpression("begins_with(SK, :sk)")
                .withScanIndexForward(false)
                .withLimit(1);
    }
}
