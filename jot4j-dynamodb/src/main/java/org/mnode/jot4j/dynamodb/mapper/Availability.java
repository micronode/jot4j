package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;
import net.fortuna.ical4j.model.component.VAvailability;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Availability extends AbstractCalMapper {

    @DynamoDBAttribute(attributeName = "Uid")
    private String uid;

    @DynamoDBVersionAttribute(attributeName = "Sequence")
    private Integer sequence;

    @DynamoDBAttribute(attributeName = "Data")
    @DynamoDBTypeConverted(converter = VAvailabilityConverter.class)
    private VAvailability data;

    @DynamoDBAttribute(attributeName = "Categories")
    private Set<String> categories;

    @Override
    @DynamoDBHashKey(attributeName = "PK")
    public String getPK() {
        return "AVAILABILITY#" + uid;
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
        return "AVAILABILITY";
    }
}
