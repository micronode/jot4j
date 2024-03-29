package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class EventRecurrence extends Event {

    @DynamoDBAttribute(attributeName = "RecurrenceId")
    private String recurrenceId;

    @Override
    @DynamoDBRangeKey(attributeName = "SK")
    public String getSK() {
        return "RECURRENCE#" + recurrenceId + "#SEQUENCE#" + sequence;
    }

    @Override
    @DynamoDBAttribute(attributeName = "TYPE")
    public String getType() {
        return "EVENT_RECURRENCE";
    }
}
