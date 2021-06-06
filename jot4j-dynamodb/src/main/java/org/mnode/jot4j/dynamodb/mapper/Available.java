package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import lombok.*;

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

    @Override
    @DynamoDBAttribute(attributeName = "TYPE")
    public String getType() {
        return "AVAILABLE";
    }
}
