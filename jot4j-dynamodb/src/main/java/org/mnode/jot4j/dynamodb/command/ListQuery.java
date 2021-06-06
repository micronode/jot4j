package org.mnode.jot4j.dynamodb.command;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;

public interface ListQuery {

    /**
     * Return matching items of the specified type associated with the specified calendar.
     *
     * @param calendarUid a calendar UID
     * @param type an item type (e.g. EVENT, JOURNAL, TODO, etc.)
     * @param <T> mapper type for results
     * @return a query expression
     */
    default <T> DynamoDBQueryExpression<T> listCalendarComponents(String calendarUid, String type) {
        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put("pk", new AttributeValue().withS("CALENDAR#" + calendarUid));

        return new DynamoDBQueryExpression<T>()
                .withFilterExpression("TYPE = CALENDAR_" + type)
                .withExpressionAttributeValues(attributes)
                .withKeyConditionExpression("PK = :pk");
    }

    /**
     * Return the calendar(s) associated with the specified component.
     *
     * @param uid a component UID
     * @param type an item type (e.g. EVENT, JOURNAL, TODO, etc.)
     * @param <T> mapper type for results
     * @return a query expression
     */
    default <T> DynamoDBQueryExpression<T> listComponentCalendars(String uid, String type) {
        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put("pk", new AttributeValue().withS(type + "#" + uid));

        return new DynamoDBQueryExpression<T>()
                .withIndexName("GSI_1")
                .withFilterExpression("TYPE = CALENDAR_" + type)
                .withExpressionAttributeValues(attributes)
                .withKeyConditionExpression("PK = :pk");
    }

    /**
     * Return all recurrence overrides of the specified type with the specified UID.
     *
     * @param uid a component UID
     * @param type an item type (e.g. EVENT, JOURNAL, TODO, etc.)
     * @param <T> mapper type for results
     * @return a query expression
     */
    default <T> DynamoDBQueryExpression<T> listComponentRecurrences(String uid, String type) {
        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put("pk", new AttributeValue().withS(type + "#" + uid));
        attributes.put("sk", new AttributeValue().withS("RECURRENCE#"));

        return new DynamoDBQueryExpression<T>()
                .withFilterExpression("TYPE = " + type + "_RECURRENCE")
                .withExpressionAttributeValues(attributes)
                .withKeyConditionExpression("PK = :pk")
                .withKeyConditionExpression("begins_with(SK, :sk)");
    }

    /**
     * Return all alarms associated with the specified UID.
     *
     * @param uid a component UID
     * @param type a component type (e.g. EVENT, JOURNAL, TODO, etc.)
     * @param <T> mapper type for results
     * @return a query expression
     */
    default <T> DynamoDBQueryExpression<T> listComponentAlarms(String uid, String type) {
        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put("pk", new AttributeValue().withS(type + "#" + uid));
        attributes.put("sk", new AttributeValue().withS("ALARM#"));

        return new DynamoDBQueryExpression<T>()
                .withFilterExpression("TYPE = " + type + "_ALARM")
                .withExpressionAttributeValues(attributes)
                .withKeyConditionExpression("PK = :pk")
                .withKeyConditionExpression("begins_with(SK, :sk)");
    }

    /**
     * Return all organizers associated with the specified UID.
     *
     * @param uid a component UID
     * @param type a component type (e.g. EVENT, JOURNAL, TODO, etc.)
     * @param <T> mapper type for results
     * @return a query expression
     */
    default <T> DynamoDBQueryExpression<T> listComponentOrganizers(String uid, String type) {
        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put("pk", new AttributeValue().withS(type + "#" + uid));
        attributes.put("sk", new AttributeValue().withS("ORGANIZER#"));

        return new DynamoDBQueryExpression<T>()
                .withFilterExpression("TYPE = " + type + "_ORGANIZER")
                .withExpressionAttributeValues(attributes)
                .withKeyConditionExpression("PK = :pk")
                .withKeyConditionExpression("begins_with(SK, :sk)");
    }

    /**
     * Return all attendees associated with the specified UID.
     *
     * @param uid a component UID
     * @param type a component type (e.g. EVENT, JOURNAL, TODO, etc.)
     * @param <T> mapper type for results
     * @return a query expression
     */
    default <T> DynamoDBQueryExpression<T> listComponentAttendees(String uid, String type) {
        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put("pk", new AttributeValue().withS(type + "#" + uid));
        attributes.put("sk", new AttributeValue().withS("ATTENDEE#"));

        return new DynamoDBQueryExpression<T>()
                .withFilterExpression("TYPE = " + type + "_ATTENDEE")
                .withExpressionAttributeValues(attributes)
                .withKeyConditionExpression("PK = :pk")
                .withKeyConditionExpression("begins_with(SK, :sk)");
    }

    /**
     * Return all revisions of the specified type with the specified UID.
     *
     * @param uid a component UID
     * @param type an item type (e.g. EVENT, JOURNAL, TODO, etc.)
     * @param <T> mapper type for results
     * @return a query expression
     */
    default <T> DynamoDBQueryExpression<T> listComponentRevisions(String uid, String type) {
        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put("pk", new AttributeValue().withS(type + "#" + uid));

        return new DynamoDBQueryExpression<T>()
                .withFilterExpression("TYPE = " + type)
                .withExpressionAttributeValues(attributes)
                .withKeyConditionExpression("PK = :pk");
    }

    /**
     * Return all revisions of the specified type with the specified UID and recurrence ID.
     *
     * @param uid a component UID
     * @param recurrenceId a component recurrence ID
     * @param type an item type (e.g. EVENT, JOURNAL, TODO, etc.)
     * @param <T> mapper type for results
     * @return a query expression
     */
    default <T> DynamoDBQueryExpression<T> listRecurrenceRevisions(String uid, String recurrenceId, String type) {
        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put("pk", new AttributeValue().withS(type + "#" + uid));
        attributes.put("sk", new AttributeValue().withS("RECURRENCE#" + recurrenceId));

        return new DynamoDBQueryExpression<T>()
                .withFilterExpression("TYPE = " + type + "_RECURRENCE")
                .withExpressionAttributeValues(attributes)
                .withKeyConditionExpression("PK = :pk")
                .withKeyConditionExpression("begins_with(SK, :sk)");
    }
}
