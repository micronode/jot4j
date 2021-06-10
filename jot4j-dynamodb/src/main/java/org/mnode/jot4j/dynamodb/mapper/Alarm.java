package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;
import net.fortuna.ical4j.model.component.VAlarm;

@EqualsAndHashCode
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Alarm extends AbstractCalMapper {

    @DynamoDBAttribute(attributeName = "Uid")
    private String uid;

    @DynamoDBAttribute(attributeName = "Data")
    @DynamoDBTypeConverted(converter = VAlarmConverter.class)
    private VAlarm data;

    @DynamoDBAttribute(attributeName = "ComponentUid")
    private String componentUid;

    @Override
    @DynamoDBHashKey(attributeName = "PK")
    public String getPK() {
        return "ALARM#" + uid;
    }

    @Override
    @DynamoDBRangeKey(attributeName = "SK")
    public String getSK() {
        return getPK();
    }

    @Override
    @DynamoDBAttribute(attributeName = "TYPE")
    public String getType() {
        return "ALARM";
    }

    @DynamoDBIndexHashKey(attributeName = "GSI3_PK", globalSecondaryIndexName = "GSI3")
    public String getGSI3PK() {
        return getComponentUid();
    }

    @DynamoDBIndexRangeKey(attributeName = "GSI3_SK", globalSecondaryIndexName = "GSI3")
    public String getGSI3SK() {
        return getSK();
    }
}
