package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Available extends AbstractCalMapper {

    @DynamoDBAttribute(attributeName = "Uid")
    private String uid;

    @DynamoDBAttribute(attributeName = "Data")
    @DynamoDBTypeConverted(converter = AvailableConverter.class)
    private net.fortuna.ical4j.model.component.Available data;

    @DynamoDBAttribute(attributeName = "AvailabilityUid")
    private String availabilityUid;

    @DynamoDBAttribute(attributeName = "DtStart")
    private Date startDate;

    @Override
    @DynamoDBHashKey(attributeName = "PK")
    public String getPK() {
        return "AVAILABLE#" + uid;
    }

    @Override
    @DynamoDBRangeKey(attributeName = "SK")
    public String getSK() {
        return getPK();
    }

    @DynamoDBIndexHashKey(attributeName = "GSI3_PK", globalSecondaryIndexName = "GSI3")
    public String getGSI3PK() {
        return getAvailabilityUid();
    }

    @DynamoDBIndexRangeKey(attributeName = "GSI3_SK", globalSecondaryIndexName = "GSI3")
    public Date getGSI3SK() {
        return getStartDate();
    }

    @Override
    @DynamoDBAttribute(attributeName = "TYPE")
    public String getType() {
        return "AVAILABLE";
    }
}
