package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;
import net.fortuna.ical4j.model.component.VEvent;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Event extends AbstractCalMapper {

    @DynamoDBAttribute(attributeName = "Uid")
    private String uid;

    @DynamoDBVersionAttribute(attributeName = "Sequence")
    protected Integer sequence;

    @DynamoDBAttribute(attributeName = "Data")
    @DynamoDBTypeConverted(converter = VEventConverter.class)
    private VEvent data;

    @Override
    @DynamoDBHashKey(attributeName = "PK")
    public String getPK() {
        return "EVENT#" + uid;
    }

    @Override
    @DynamoDBRangeKey(attributeName = "SK")
    public String getSK() {
        return "SEQUENCE#" + sequence;
    }

    @DynamoDBIndexHashKey(attributeName = "GSI1_PK", globalSecondaryIndexName = "GSI1")
    public String getGSI1PK() {
        return getPK();
    }

    @DynamoDBIndexRangeKey(attributeName = "GSI1_SK", globalSecondaryIndexName = "GSI1")
    public String getGSI1SK() {
        return getSK();
    }

    @Override
    @DynamoDBAttribute(attributeName = "TYPE")
    public String getType() {
        return "EVENT";
    }
}
