package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class JournalRecurrence extends Journal {

    @DynamoDBAttribute(attributeName = "RecurrenceId")
    private String recurrenceId;

    @Override
    @DynamoDBRangeKey(attributeName = "SK")
    public String getSK() {
        return "SEQUENCE#" + sequence + "#RECURRENCE#" + recurrenceId;
    }

    @Override
    @DynamoDBAttribute(attributeName = "TYPE")
    public String getType() {
        return "JOURNAL_RECURRENCE";
    }
}
